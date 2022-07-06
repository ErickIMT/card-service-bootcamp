package com.nttdata.cardservice.infrastructure.service;

import com.nttdata.cardservice.application.feign.customer.CustomerClient;
import com.nttdata.cardservice.application.feign.products.ProductClient;
import com.nttdata.cardservice.application.feign.transactions.TransactionClient;
import com.nttdata.cardservice.domain.dto.BankAccountDTO;
import com.nttdata.cardservice.domain.dto.CustomerDTO;
import com.nttdata.cardservice.domain.dto.DebitResDTO;
import com.nttdata.cardservice.domain.dto.TransactionDTO;
import com.nttdata.cardservice.infrastructure.document.DebitCard;
import com.nttdata.cardservice.infrastructure.repository.DebitCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DebitCardServiceImpl implements  DebitCardService{

  @Autowired
  private DebitCardRepository debitCardRepository;
  @Autowired
  private CustomerClient customerClient;
  @Autowired
  private ProductClient productClient;
  @Autowired
  private TransactionClient transactionClient;

  @Override
  public Flux<DebitCard> getAll() {
    return debitCardRepository.findAll();
  }

  @Override
  public Mono<DebitCard> getDebitCardById(String id) {
    return debitCardRepository.findById(id);
  }

  @Override
  public Mono<DebitCard> saveDebitCard(DebitCard debitCard) {

    CustomerDTO customerDTO = customerClient.getCustomerById(debitCard.getCustomerDTO().getId());
    if(customerDTO.equals(null)) {
      return Mono.error(new Exception("no se encontro idCustomer"));
    }

    BankAccountDTO bankAccountDTO = searchBankAcount(debitCard.getMainAccount().getId());
    if (bankAccountDTO.equals(null)) {
      return Mono.error(new Exception("No se encontro cuenta bancaria"));
    }

    List<BankAccountDTO> accounts = debitCard.getAccountsList().stream()
      .map(BankAccountDTO::getId)
      .map(this::searchBankAcount)
      .collect(Collectors.toList());

    debitCard.setMainAccount(bankAccountDTO);
    debitCard.setAccountsList(accounts);
    debitCard.setCardNumber("4859510" + Math.floor(Math.random())*999999999);
    debitCard.setCustomerDTO(customerDTO);

    return debitCardRepository.save(debitCard);
  }

  @Override
  public Mono<DebitResDTO> getLastTransactions(String id) {

    DebitResDTO debitResDTO = new DebitResDTO();


    Mono<BankAccountDTO> bankAccountDTO = getDebitCardById(id)
      .map(dc -> dc.getMainAccount().getId())
      .map(this::searchBankAcount)
      .map(ba -> {
        debitResDTO.setIdAccount(ba.getId());
        debitResDTO.setAccount(ba.getAccount());
        debitResDTO.setAmount(ba.getAmount());
        debitResDTO.setAmount(ba.getAmount());
        return ba;
      }).switchIfEmpty(Mono.error(new Exception("No se encontro cuenta de banco")));

    List<TransactionDTO> transactions = transactionClient.getTransactionsByAccountId(debitResDTO.getIdAccount())
      .stream()
      .limit(10)
      .collect(Collectors.toList());

    debitResDTO.setTransactions(transactions);

    return Mono.just(debitResDTO);

  }

  private BankAccountDTO searchBankAcount(String id) {

    BankAccountDTO bankAccountDTO = productClient.getBankAccountById(id);

    return bankAccountDTO;

  }
}

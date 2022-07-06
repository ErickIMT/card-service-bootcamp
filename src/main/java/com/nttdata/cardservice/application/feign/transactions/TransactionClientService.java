package com.nttdata.cardservice.application.feign.transactions;

import com.nttdata.cardservice.domain.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionClientService {

  @Autowired
  private TransactionClient transactionClient;

  public List<TransactionDTO> getTransactionsByAccountId(String id) {
    return transactionClient.getTransactionsByAccountId(id);
  }
}

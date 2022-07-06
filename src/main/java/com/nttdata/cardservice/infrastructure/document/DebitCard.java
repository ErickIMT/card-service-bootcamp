package com.nttdata.cardservice.infrastructure.document;

import com.nttdata.cardservice.domain.dto.BankAccountDTO;
import com.nttdata.cardservice.domain.dto.CustomerDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "debitcard")
public class DebitCard {

  @Id
  private String id;
  private CustomerDTO customerDTO;
  private String cardNumber;
  private BankAccountDTO mainAccount;
  private List<BankAccountDTO> accountsList;
}

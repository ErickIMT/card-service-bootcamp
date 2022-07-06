package com.nttdata.cardservice.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class DebitResDTO {

  private String id;
  private String typeAccount;
  private String idAccount;
  private String account;
  private float amount;
  private List<TransactionDTO> transactions;

}

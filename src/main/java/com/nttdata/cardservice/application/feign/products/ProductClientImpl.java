package com.nttdata.cardservice.application.feign.products;

import com.nttdata.cardservice.domain.dto.BankAccountDTO;
import com.nttdata.cardservice.domain.dto.CreditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductClientImpl {

  @Autowired
  private ProductClient productClient;

  private BankAccountDTO getBankAccountById(String id) {
    return productClient.getBankAccountById(id);
  }

  private CreditDTO getCreditById(String id) {
    return productClient.getCreditById(id);
  }
}

package com.nttdata.cardservice.application.feign.products;

import com.nttdata.cardservice.domain.dto.BankAccountDTO;
import com.nttdata.cardservice.domain.dto.CreditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "product-service" , url = "localhost:/8092")
public interface ProductClient {

  @GetMapping("/bankAccounts/{id}")
  BankAccountDTO getBankAccountById(@PathVariable("id") String id);

  @GetMapping("/credits/{id}")
  CreditDTO getCreditById(@PathVariable("id") String id);
}

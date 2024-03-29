package com.nttdata.cardservice.application.feign.customer;

import com.nttdata.cardservice.domain.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "customer-service", url = "https://customer-service-bootcamp.azurewebsites.net/customers")
public interface CustomerClient {

  @GetMapping("/{id}")
  CustomerDTO getCustomerById(@PathVariable("id") String id);
}

package com.nttdata.cardservice.application.feign.customer;

import com.nttdata.cardservice.domain.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerClientService {
  @Autowired
  private CustomerClient customerClient;

  public CustomerDTO getCustomerById(String id) {
    return customerClient.getCustomerById(id);
  }
}

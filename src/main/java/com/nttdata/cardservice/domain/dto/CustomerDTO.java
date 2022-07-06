package com.nttdata.cardservice.domain.dto;

import lombok.Data;

@Data
public class CustomerDTO {

    private String id;
    private String name;
    private String lastName;
    private String document;
    private String address;
    private String email;
    private String phone;
    private CustomerTypeDTO customerType;

}

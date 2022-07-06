package com.nttdata.cardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@FeignClient
@EnableEurekaClient
public class CardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardServiceApplication.class, args);
	}

}

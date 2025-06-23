package com.spring.carservice.services;

import com.spring.carservice.dtos.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "${customer.service.url}")
public interface CustomerService {
    @GetMapping("customers/{id}")
    public CustomerDTO getCustomer(@PathVariable int id);
}

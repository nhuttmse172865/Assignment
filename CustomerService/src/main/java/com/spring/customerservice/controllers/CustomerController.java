package com.spring.customerservice.controllers;

import com.spring.customerservice.dtos.CustomerDTO;
import com.spring.customerservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<?> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int id) {
        return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.createCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.updateCustomer(id, customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }
}

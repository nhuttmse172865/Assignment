package com.spring.customerservice.services;

import com.spring.customerservice.dtos.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public CustomerDTO getCustomer(int id);
    public List<CustomerDTO> getAllCustomers();
    public CustomerDTO createCustomer(CustomerDTO customer);
    public CustomerDTO updateCustomer(int id, CustomerDTO customer);
    public boolean deleteCustomer(int id);
}

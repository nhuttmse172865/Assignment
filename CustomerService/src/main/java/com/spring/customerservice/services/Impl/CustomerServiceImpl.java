package com.spring.customerservice.services.Impl;

import com.spring.customerservice.dtos.CustomerDTO;
import com.spring.customerservice.pojos.Customer;
import com.spring.customerservice.repositories.CustomerRepository;
import com.spring.customerservice.services.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO getCustomer(int id) {
        try {
            CustomerDTO customerDTO = new CustomerDTO();
            Customer customer = customerRepository.getCustomerByCustomerID(id);
            BeanUtils.copyProperties(customer, customerDTO);
            return customerDTO;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        try{
            List<Customer> customers = customerRepository.findAll();
            List<CustomerDTO> customerDTOs = new ArrayList<>();
            customers.forEach(customer -> {
                CustomerDTO customerDTO = new CustomerDTO();
                BeanUtils.copyProperties(customer, customerDTO);
                customerDTOs.add(customerDTO);
            });
            return customerDTOs;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
       try {
           Customer customer = new Customer();
           customer.setCustomerName(customerDTO.getCustomerName());
           customer.setCustomerBirthday(customerDTO.getCustomerBirthday());
           customer.setEmail(customerDTO.getEmail());
           customer.setTelephone(customerDTO.getTelephone());
           customer.setCustomerStatus(customerDTO.getCustomerStatus());
           customer.setPassword(customerDTO.getPassword());
           customer.setRole("CUSTOMER");
           CustomerDTO customerDTOReturn = new CustomerDTO();
           BeanUtils.copyProperties(customerRepository.save(customer), customerDTOReturn);
           return customerDTOReturn;
       } catch (RuntimeException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) {
        try{
            Customer customer = customerRepository.getCustomerByCustomerID(id);
            customer.setCustomerName(customerDTO.getCustomerName());
            customer.setCustomerBirthday(customerDTO.getCustomerBirthday());
            customer.setEmail(customerDTO.getEmail());
            customer.setTelephone(customerDTO.getTelephone());
            customer.setCustomerStatus(customerDTO.getCustomerStatus());
            customer.setPassword(customerDTO.getPassword());
            CustomerDTO customerDTOReturn = new CustomerDTO();
            BeanUtils.copyProperties(customerRepository.save(customer), customerDTOReturn);
            return customerDTOReturn;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCustomer(int id) {
        try {
            customerRepository.deleteById(id);
            return true;
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}

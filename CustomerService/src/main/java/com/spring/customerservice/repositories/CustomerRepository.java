package com.spring.customerservice.repositories;

import com.spring.customerservice.pojos.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer getCustomerByCustomerID(int customerID);
}

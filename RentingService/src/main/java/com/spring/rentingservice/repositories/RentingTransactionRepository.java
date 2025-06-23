package com.spring.rentingservice.repositories;

import com.spring.rentingservice.pojos.RentingTransaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RentingTransactionRepository extends JpaRepository<RentingTransaction, Integer> {
    RentingTransaction getRentingTransactionByRentingTransactionID(int rentingTransactionID);

    List<RentingTransaction> getRentingTransactionsByCustomerID(int customerID);

    List<RentingTransaction> getRentingTransactionsByRentingDateAfterAndRentingDateBefore(Date rentingDateAfter, Date rentingDateBefore, Sort rentingDate);
}

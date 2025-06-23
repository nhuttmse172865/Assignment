package com.spring.rentingservice.services;

import com.spring.rentingservice.dtos.RentingTransactionDTO;

import java.util.List;


public interface RentingService {
    public RentingTransactionDTO getRentingTransactionById(int id);
    public List<RentingTransactionDTO> getAllRentingTransactions();
    public RentingTransactionDTO createRentingTransaction(RentingTransactionDTO rentingTransactionDTO);
    public RentingTransactionDTO updateRentingTransaction(int id,RentingTransactionDTO rentingTransactionDTO);
    public boolean deleteRentingTransaction(int id);
    public List<RentingTransactionDTO> getRentingTransactionsByCustomerId(int customerId);
}

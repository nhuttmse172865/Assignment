package com.spring.rentingservice.controllers;

import com.spring.rentingservice.dtos.RentingTransactionDTO;
import com.spring.rentingservice.services.RentingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/renting")
@CrossOrigin
public class RentingController {

    @Autowired
    private RentingService rentingService;

    @GetMapping("")
    public ResponseEntity<List<RentingTransactionDTO>> getAllRentingTransactions() {
        return new ResponseEntity<>(rentingService.getAllRentingTransactions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentingTransactionDTO> getRentingTransaction(@PathVariable int id) {
        return new ResponseEntity<>(rentingService.getRentingTransactionById(id), HttpStatus.OK);
    }

    @GetMapping("/view-history/{customerId}")
    public ResponseEntity<List<RentingTransactionDTO>> getRentingTransactionHistoryByCustomerId(@PathVariable int customerId) {
        return new ResponseEntity<>(rentingService.getRentingTransactionsByCustomerId(customerId), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createRentingTransaction(@RequestBody RentingTransactionDTO rentingTransactionDTO) {
        return new ResponseEntity<>(rentingService.createRentingTransaction(rentingTransactionDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRentingTransaction(@PathVariable int id, @RequestBody RentingTransactionDTO rentingTransactionDTO) {
        return new ResponseEntity<>(rentingService.updateRentingTransaction(id, rentingTransactionDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRentingTransaction(@PathVariable int id) {
        return new ResponseEntity<>(rentingService.deleteRentingTransaction(id), HttpStatus.OK);
    }



}

package com.spring.carservice.controllers;

import com.spring.carservice.dtos.SupplierDTO;
import com.spring.carservice.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;


    @GetMapping("")
    public ResponseEntity<?> getSuppliers() {
       return new ResponseEntity<>(supplierService.getSuppliers(), HttpStatus.OK);
    }

    @GetMapping("/{supplierID}")
    public ResponseEntity<?> getSupplier(@PathVariable  int supplierID) {
        return new ResponseEntity<>(supplierService.getSupplier(supplierID), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        return new ResponseEntity<>(supplierService.createSupplier(supplierDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{supplierID}")
    public ResponseEntity<?> updateSupplier(@PathVariable int supplierID, @RequestBody SupplierDTO supplierDTO) {
        return new ResponseEntity<>(supplierService.updateSupplier(supplierID, supplierDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{supplierID}")
    public ResponseEntity<?> deleteSupplier(@PathVariable int supplierID) {
        return new ResponseEntity<>(supplierService.deleteSupplier(supplierID), HttpStatus.OK);
    }

}

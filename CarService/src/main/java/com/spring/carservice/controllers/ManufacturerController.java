package com.spring.carservice.controllers;

import com.spring.carservice.dtos.ManufacturerDTO;
import com.spring.carservice.services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("")
    public ResponseEntity<?> getManufacturers() {
        return new ResponseEntity<>(manufacturerService.getAllManufacturers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getManufacturerById(@PathVariable int id) {
        return new ResponseEntity<>(manufacturerService.getManufacturer(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) {
        return new ResponseEntity<>(manufacturerService.createManufacturer(manufacturerDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateManufacturer(@PathVariable int id, @RequestBody ManufacturerDTO manufacturerDTO) {
        return new ResponseEntity<>(manufacturerService.updateManufacturer(id, manufacturerDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteManufacturer(@PathVariable int id) {
        return new ResponseEntity<>(manufacturerService.deleteManufacturer(id),HttpStatus.OK);
    }
}

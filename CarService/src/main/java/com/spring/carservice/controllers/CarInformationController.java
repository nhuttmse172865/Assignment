package com.spring.carservice.controllers;

import com.spring.carservice.dtos.CarInformationDTO;
import com.spring.carservice.services.CarInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cars")
public class CarInformationController {

    @Autowired
    private CarInformationService carInformationService;

    @GetMapping("")
    public ResponseEntity<?> getCarsInformation() {
        return new ResponseEntity<>(carInformationService.getCarsInformation(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarInformationDTO> getCarInformationById(@PathVariable int id) {
        return new ResponseEntity<>(carInformationService.getCarInformationById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addCarInformation(@RequestBody CarInformationDTO carInformationDTO) {
        return new ResponseEntity<>(carInformationService.createCarInformation(carInformationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarInformation(@PathVariable int id, @RequestBody CarInformationDTO carInformationDTO) {
        return new ResponseEntity<>(carInformationService.updateCarInformation(id, carInformationDTO), HttpStatus.OK);
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<Boolean> updateCarInformationStatus(@PathVariable int id, @RequestBody Byte status) {
        return new ResponseEntity<>(carInformationService.updateCarInformationStatus(id, status), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarInformation(@PathVariable int id,  @RequestBody int customerID) {
        return new ResponseEntity<>(carInformationService.deleteCarInformation(id, customerID), HttpStatus.OK);
    }

}

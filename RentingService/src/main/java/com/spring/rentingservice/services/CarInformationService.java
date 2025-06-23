package com.spring.rentingservice.services;


import com.spring.rentingservice.dtos.CarInformationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "car-service", url = "${car.service.url}")
public interface CarInformationService {
    @GetMapping("cars/{id}")
    public CarInformationDTO getCarInformationById(@PathVariable int id);

    @PutMapping("cars/update-status/{id}")
    public Boolean updateCarInformationStatus(@PathVariable int id, @RequestBody Byte status);
}

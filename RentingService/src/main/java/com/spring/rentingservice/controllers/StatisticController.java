package com.spring.rentingservice.controllers;

import com.spring.rentingservice.dtos.RentingDetailDTO;
import com.spring.rentingservice.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/statistic")
@CrossOrigin
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("")
    public ResponseEntity<?> getStatisticByPeriod(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam int customerID) {
        return new ResponseEntity<>(statisticService.getStatisticByPeriod(startDate, endDate, customerID), HttpStatus.OK);
    }
}

package com.spring.rentingservice.services;

import com.spring.rentingservice.dtos.RentingTransactionDTO;

import java.time.LocalDate;
import java.util.List;

public interface StatisticService {
    public List<RentingTransactionDTO> getStatisticByPeriod(LocalDate startDate, LocalDate endDate, int customerID);
}

package com.spring.rentingservice.services.Impl;

import com.spring.rentingservice.dtos.CustomerDTO;
import com.spring.rentingservice.dtos.RentingDetailDTO;
import com.spring.rentingservice.dtos.RentingTransactionDTO;
import com.spring.rentingservice.pojos.RentingTransaction;
import com.spring.rentingservice.repositories.RentingTransactionRepository;
import com.spring.rentingservice.services.CustomerService;
import com.spring.rentingservice.services.StatisticService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private RentingTransactionRepository rentingTransactionRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public List<RentingTransactionDTO> getStatisticByPeriod(LocalDate startDate, LocalDate endDate, int customerID) {
        try{
            CustomerDTO customerDTO = customerService.getCustomer(customerID);
            if(!customerDTO.getRole().equals("ADMIN")) throw new RuntimeException("Unauthorized");
            List<RentingTransactionDTO> rentingTransactionDTOs = new ArrayList<>();
            List<RentingTransaction> rentingTransactions = rentingTransactionRepository.getRentingTransactionsByRentingDateAfterAndRentingDateBefore(
                    Date.from(startDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                    Date.from(endDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                    Sort.by("rentingDate").descending()
                    );
            rentingTransactions.forEach(rentingTransaction -> {
                if (rentingTransaction.getRentingStatus() != 0){
                    rentingTransactionDTOs.add(convertRentingTransactionDTO(rentingTransaction));
                }
            });
            return rentingTransactionDTOs;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    private RentingTransactionDTO convertRentingTransactionDTO(RentingTransaction rentingTransaction) {
        RentingTransactionDTO rentingTransactionDTO = new RentingTransactionDTO();
        BeanUtils.copyProperties(rentingTransaction, rentingTransactionDTO);
        List<RentingDetailDTO> rentingDetailDTOS = new ArrayList<>();
        rentingTransaction.getRentingDetail().forEach(rentingDetail -> {
            RentingDetailDTO rentingDetailDTO = new RentingDetailDTO();
            BeanUtils.copyProperties(rentingDetail, rentingDetailDTO);
            rentingDetailDTO.setCarID(rentingDetail.getId().getCarID());
            rentingDetailDTO.setRentingTransactionID(rentingDetail.getId().getRentingTransactionID());
            rentingDetailDTOS.add(rentingDetailDTO);
        });
        rentingTransactionDTO.setRentingDetails(rentingDetailDTOS);
        return rentingTransactionDTO;
    }
}

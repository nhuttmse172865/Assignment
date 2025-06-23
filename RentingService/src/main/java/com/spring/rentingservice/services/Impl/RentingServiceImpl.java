package com.spring.rentingservice.services.Impl;

import com.spring.rentingservice.dtos.CarInformationDTO;
import com.spring.rentingservice.dtos.CustomerDTO;
import com.spring.rentingservice.dtos.RentingDetailDTO;
import com.spring.rentingservice.dtos.RentingTransactionDTO;
import com.spring.rentingservice.pojos.RentingDetail;
import com.spring.rentingservice.pojos.RentingDetailID;
import com.spring.rentingservice.pojos.RentingTransaction;
import com.spring.rentingservice.repositories.RentingDetailRepository;
import com.spring.rentingservice.repositories.RentingTransactionRepository;
import com.spring.rentingservice.services.CarInformationService;
import com.spring.rentingservice.services.CustomerService;
import com.spring.rentingservice.services.RentingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RentingServiceImpl implements RentingService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CarInformationService carInformationService;

    @Autowired
    private RentingTransactionRepository transactionRepository;
    @Autowired
    private RentingDetailRepository rentingDetailRepository;


    @Override
    public RentingTransactionDTO getRentingTransactionById(int id) {
        return convertRentingTransactionDTO(transactionRepository.getRentingTransactionByRentingTransactionID(id));
    }

    @Override
    public List<RentingTransactionDTO> getAllRentingTransactions() {
        List<RentingTransaction> rentingTransactions = transactionRepository.findAll();
        List<RentingTransactionDTO> rentingTransactionDTOs = new ArrayList<>();
        rentingTransactions.forEach(rentingTransaction -> {
            if(rentingTransaction.getRentingStatus() != 0){
                rentingTransactionDTOs.add(convertRentingTransactionDTO(rentingTransaction));
            }
        });
        return rentingTransactionDTOs;
    }

    @Transactional
    @Override
    public RentingTransactionDTO createRentingTransaction(RentingTransactionDTO rentingTransactionDTO) {
       try {

           CustomerDTO customerDTO =  customerService.getCustomer(rentingTransactionDTO.getCustomerID());
           if(!customerDTO.getRole().equals("CUSTOMER")) throw new RuntimeException("Unauthorized");
           RentingTransaction rentingTransaction = new RentingTransaction();
           rentingTransaction.setCustomerID(customerDTO.getCustomerID());
           rentingTransaction.setRentingDate(new Date());
           rentingTransaction.setRentingStatus((byte) 1);
           List<RentingDetail> details = rentingTransactionDTO.getRentingDetails().stream().map(detailDTO -> {
               RentingDetail rentingDetail = new RentingDetail();
               RentingDetailID detailId = new RentingDetailID();
               CarInformationDTO carInformationDTO = carInformationService.getCarInformationById(detailDTO.getCarID());
               if(rentingDetailRepository.
                       getRentingDetailByStartDateLessThanEqualAndEndDateGreaterThanEqualAndId_CarID
                               (detailDTO.getStartDate(), detailDTO.getStartDate(),detailDTO.getCarID()) != null ||
                       rentingDetailRepository.
                               getRentingDetailByStartDateLessThanEqualAndEndDateGreaterThanEqualAndId_CarID
                                       (detailDTO.getEndDate(), detailDTO.getEndDate(),detailDTO.getCarID()) != null
               )
                   throw  new RuntimeException("Car "+ carInformationDTO.getCarName() +" has been rented!");
               detailId.setCarID(carInformationDTO.getCarID());
               rentingDetail.setId(detailId);
               rentingDetail.setStartDate(detailDTO.getStartDate());
               rentingDetail.setEndDate(detailDTO.getEndDate());
               BigDecimal price = calculatePriceRenting(carInformationDTO.getCarRentingPricePerDay(),detailDTO.getStartDate(),detailDTO.getEndDate());
               rentingDetail.setPrice(price);
               rentingDetail.setRentingTransaction(rentingTransaction);
               return rentingDetail;
           }).toList();

           BigDecimal calculatedTotalPrice = details.stream()
                   .map(RentingDetail::getPrice)
                   .reduce(BigDecimal.ZERO, BigDecimal::add);

           rentingTransaction.setTotalPrice(calculatedTotalPrice);

           rentingTransaction.setRentingDetail(details);
           RentingTransaction savedTransaction = transactionRepository.save(rentingTransaction);

           return convertRentingTransactionDTO(savedTransaction);
       } catch (RuntimeException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public RentingTransactionDTO updateRentingTransaction(int id,RentingTransactionDTO rentingTransactionDTO) {

        RentingTransaction rentingTransaction = transactionRepository.getRentingTransactionByRentingTransactionID(id);
        BeanUtils.copyProperties(rentingTransactionDTO, rentingTransaction);

        rentingTransaction.getRentingDetail().clear();
        rentingTransactionDTO.getRentingDetails().forEach(rentingDetailDTO -> {
            RentingDetail rentingDetail = new RentingDetail();
            BeanUtils.copyProperties(rentingDetailDTO, rentingDetail);
            CarInformationDTO carInformationDTO = carInformationService.getCarInformationById(rentingDetailDTO.getCarID());
            if (carInformationDTO.getCarStatus() == 0) throw new RuntimeException("Car "+ carInformationDTO.getCarName() +" has been rented!");
            RentingDetailID detailId = new RentingDetailID();
            detailId.setCarID(carInformationDTO.getCarID());
            detailId.setRentingTransactionID(rentingTransaction.getRentingTransactionID());
            rentingDetail.setRentingTransaction(rentingTransaction);
            rentingDetail.setId(detailId);

            BigDecimal price = calculatePriceRenting(carInformationDTO.getCarRentingPricePerDay(),rentingDetailDTO.getStartDate(),rentingDetailDTO.getEndDate());
            rentingDetail.setPrice(price);

            rentingTransaction.getRentingDetail().add(rentingDetail);
        });

        BigDecimal calculatedTotalPrice = rentingTransaction.getRentingDetail().stream()
                .map(RentingDetail::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        rentingTransaction.setTotalPrice(calculatedTotalPrice);

        return convertRentingTransactionDTO(transactionRepository.save(rentingTransaction));
    }

    @Override
    public boolean deleteRentingTransaction(int id) {
       try{
           RentingTransaction rentingTransaction = transactionRepository.getRentingTransactionByRentingTransactionID(id);
            rentingTransaction.setRentingStatus((byte)0);
           return true;
       } catch (RuntimeException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public List<RentingTransactionDTO> getRentingTransactionsByCustomerId(int customerId) {
        List<RentingTransaction> rentingTransactions = transactionRepository.getRentingTransactionsByCustomerID(customerId);
        List<RentingTransactionDTO> rentingTransactionDTOs = new ArrayList<>();
        rentingTransactions.forEach(rentingTransaction -> {
            rentingTransactionDTOs.add(convertRentingTransactionDTO(rentingTransaction));
        });
        return rentingTransactionDTOs;
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

    private BigDecimal calculatePriceRenting (BigDecimal pricePerDay, Date startDate, Date endDate) {
        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long numberOfDays = Duration.between(startLocalDate.atStartOfDay(), endLocalDate.atStartOfDay()).toDays();
        BigDecimal numberOfDaysAsBigDecimal = BigDecimal.valueOf(numberOfDays);
        return pricePerDay.multiply(numberOfDaysAsBigDecimal);
    }

}

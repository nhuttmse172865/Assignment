package com.spring.rentingservice.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class RentingTransactionDTO {
        private int rentingTransactionID;
        private Date rentingDate;
        private BigDecimal totalPrice;
        private int customerID;
        private Byte rentingStatus;
        private List<RentingDetailDTO> rentingDetails;
}

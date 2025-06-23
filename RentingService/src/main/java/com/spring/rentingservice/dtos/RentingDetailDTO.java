package com.spring.rentingservice.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RentingDetailDTO {
    private int rentingTransactionID;
    private int carID;
    private Date startDate;
    private Date endDate;
    private BigDecimal price;
}

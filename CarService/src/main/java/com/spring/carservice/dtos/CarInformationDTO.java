package com.spring.carservice.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarInformationDTO {
    private int carID;
    private String carName;
    private String carDescription;
    private int numberOfDoors;
    private int seatingCapacity;
    private String fuelType;
    private int year;
    private ManufacturerDTO manufacturerID;
    private SupplierDTO supplierID;
    private Byte carStatus;
    private BigDecimal carRentingPricePerDay;
    private int customerID;
}

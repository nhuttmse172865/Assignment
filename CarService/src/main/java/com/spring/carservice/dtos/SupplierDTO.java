package com.spring.carservice.dtos;

import lombok.Data;

@Data
public class SupplierDTO {
    private int supplierID;
    private String supplierName;
    private String supplierDescription;
    private String supplierAddress;
}

package com.spring.carservice.dtos;


import lombok.Data;

@Data
public class ManufacturerDTO {
    private int manufacturerID;
    private String manufacturerName;
    private String description;
    private String manufacturerCountry;
}

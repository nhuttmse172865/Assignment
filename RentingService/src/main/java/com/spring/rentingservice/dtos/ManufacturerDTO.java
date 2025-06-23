package com.spring.rentingservice.dtos;


import lombok.Data;

@Data
public class ManufacturerDTO {
    private int manufacturerID;
    private String manufacturerName;
    private String description;
    private String manufacturerCountry;
}

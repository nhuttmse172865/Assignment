package com.spring.carservice.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDTO {
    private int customerID;
    private String customerName;
    private String telephone;
    private String email;
    private Date customerBirthday;
    private Byte customerStatus;
    private String password;
    private String role;
}

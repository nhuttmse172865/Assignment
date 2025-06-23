package com.spring.customerservice.pojos;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private int customerID;

    @Column(name = "CustomerName")
    private String customerName;

    @Column(name = "Telephone", length = 12)
    private String telephone;

    @Column(name = "Email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "CustomerBirthday")
    private Date customerBirthday;

    @Column(name = "CustomerStatus")
    private Byte customerStatus;

    @Column(name = "CustomerRole")
    private String role;

    @Column(name = "Password", length = 50)
    private String password;

}

package com.spring.carservice.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Supplier")
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SupplierID")
    private int supplierID;

    @Column(name = "SupplierName", nullable = false, length = 50)
    private String supplierName;

    @Column(name = "SupplierDescription", length = 250)
    private String supplierDescription;

    @Column(name = "SupplierAddress", length = 80)
    private String supplierAddress;

    @OneToMany(mappedBy = "supplier")
    private List<CarInformation> carInformation;
}

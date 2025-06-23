package com.spring.carservice.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Manufacturer")
@Data
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ManufacturerID")
    private int manufacturerID;

    @Column(name = "ManufacturerName", nullable = false, length = 50)
    private String manufacturerName;

    @Lob
    @Column(name = "Description")
    private String description;

    @Column(name = "ManufacturerCountry", length = 10)
    private String manufacturerCountry;

    @OneToMany(mappedBy = "manufacturer")
    private List<CarInformation> carInformation;

}

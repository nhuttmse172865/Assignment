package com.spring.carservice.pojos;

import com.spring.carservice.dtos.ManufacturerDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CarInformation")
@Data
public class CarInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarID")
    private int carID;

    @Column(name = "CarName", nullable = false, length = 50)
    private String carName;

    @Column(name = "CarDescription", length = 220)
    private String carDescription;

    @Column(name = "NumberOfDoors")
    private int numberOfDoors;

    @Column(name = "SeatingCapacity")
    private int seatingCapacity;

    @Column(name = "FuelType", length = 20)
    private String fuelType;

    @Column(name = "Year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "ManufacturerID")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "SupplierID")
    private Supplier supplier;

    @Column(name = "CarStatus")
    private Byte carStatus;

    @Column(name = "CarRentingPricePerDay")
    private BigDecimal carRentingPricePerDay;
}

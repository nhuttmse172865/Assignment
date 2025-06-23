package com.spring.rentingservice.pojos;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "RentingTransaction")
@Data
public class RentingTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RentingTransactionID")
    private int rentingTransactionID;

    @Column(name = "RentingDate")
    private Date rentingDate;

    @Column(name = "TotalPrice")
    private BigDecimal totalPrice;

    @Column(name = "CustomerID", nullable = false)
    private int customerID;

    @Column(name = "RentingStatus")
    private Byte rentingStatus;

    @OneToMany(mappedBy = "rentingTransaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RentingDetail> rentingDetail;

}

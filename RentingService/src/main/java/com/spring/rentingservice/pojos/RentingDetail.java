package com.spring.rentingservice.pojos;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "RentingDetail")
@Data
public class RentingDetail {
    @EmbeddedId
    private  RentingDetailID id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("rentingTransactionID")
    @JoinColumn(name = "renting_transaction_id")
    private RentingTransaction rentingTransaction;

    @Column(name = "StartDate", nullable = false)
    private Date startDate;

    @Column(name = "EndDate", nullable = false)
    private Date endDate;

    @Column(name = "Price")
    private BigDecimal price;

}

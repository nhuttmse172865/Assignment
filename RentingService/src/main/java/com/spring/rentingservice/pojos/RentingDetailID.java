package com.spring.rentingservice.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RentingDetailID implements Serializable {
    @Column(name = "carID")
    private int carID;


    @Column(name = "renting_transaction_id")
    private int rentingTransactionID;
}

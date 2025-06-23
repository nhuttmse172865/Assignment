package com.spring.carservice.repositories;

import com.spring.carservice.pojos.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Supplier getSuppliersBySupplierID(int supplierID);
}

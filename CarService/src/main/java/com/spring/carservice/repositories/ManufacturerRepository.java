package com.spring.carservice.repositories;

import com.spring.carservice.pojos.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
    Manufacturer getManufacturerByManufacturerID(int manufacturerID);

    void deleteManufacturerByManufacturerID(int manufacturerID);
}

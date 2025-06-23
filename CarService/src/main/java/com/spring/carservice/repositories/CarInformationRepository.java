package com.spring.carservice.repositories;

import com.spring.carservice.pojos.CarInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarInformationRepository extends JpaRepository<CarInformation, Integer> {
    CarInformation getCarInformationByCarID(int carID);
}

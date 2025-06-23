package com.spring.carservice.services;

import com.spring.carservice.dtos.CarInformationDTO;

import java.util.List;

public interface CarInformationService {
    public CarInformationDTO getCarInformationById(int id);
    public List<CarInformationDTO> getCarsInformation();
    public CarInformationDTO createCarInformation(CarInformationDTO carInformationDTO);
    public CarInformationDTO updateCarInformation(int id, CarInformationDTO carInformationDTO);
    public boolean deleteCarInformation(int id, int customerID);
    public boolean updateCarInformationStatus(int id, Byte status);
}

package com.spring.carservice.services;

import com.spring.carservice.dtos.ManufacturerDTO;
import com.spring.carservice.pojos.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    public ManufacturerDTO getManufacturer(int manufacturerID);
    public List<ManufacturerDTO> getAllManufacturers();
    public ManufacturerDTO createManufacturer(ManufacturerDTO manufacturerDTO);
    public ManufacturerDTO updateManufacturer(int manufacturerID, ManufacturerDTO manufacturerDTO);
    public boolean deleteManufacturer(int manufacturerID);
}

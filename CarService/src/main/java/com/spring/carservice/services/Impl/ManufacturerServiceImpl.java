package com.spring.carservice.services.Impl;

import com.spring.carservice.dtos.ManufacturerDTO;
import com.spring.carservice.pojos.Manufacturer;
import com.spring.carservice.repositories.ManufacturerRepository;
import com.spring.carservice.services.ManufacturerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public ManufacturerDTO getManufacturer(int manufacturerID) {
        try {
            Manufacturer manufacturer = manufacturerRepository.getManufacturerByManufacturerID(manufacturerID);
            if(manufacturer==null) return null;
            ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
            BeanUtils.copyProperties(manufacturer, manufacturerDTO);
            return manufacturerDTO;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ManufacturerDTO> getAllManufacturers() {
        try {
            List<Manufacturer> manufacturers = manufacturerRepository.findAll();
            List<ManufacturerDTO> manufacturerDTOs = new ArrayList<ManufacturerDTO>();
            manufacturers.forEach(manufacturer -> {
                ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
                BeanUtils.copyProperties(manufacturer, manufacturerDTO);
                manufacturerDTOs.add(manufacturerDTO);
            });
            return manufacturerDTOs;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ManufacturerDTO createManufacturer(ManufacturerDTO manufacturerDTO) {
      try {
          Manufacturer manufacturer = new Manufacturer();
          manufacturer.setManufacturerName(manufacturerDTO.getManufacturerName());
          manufacturer.setManufacturerCountry(manufacturerDTO.getManufacturerCountry());
          manufacturer.setDescription(manufacturerDTO.getDescription());
          ManufacturerDTO manufacturerDTOReturn = new ManufacturerDTO();
          BeanUtils.copyProperties(manufacturerRepository.save(manufacturer), manufacturerDTOReturn);
          return manufacturerDTOReturn;
      } catch (RuntimeException e) {
          throw new RuntimeException(e);
      }
    }

    @Override
    public ManufacturerDTO updateManufacturer(int manufacturerID, ManufacturerDTO manufacturerDTO) {
       try{
           Manufacturer manufacturer =  manufacturerRepository.getManufacturerByManufacturerID(manufacturerID);
           manufacturer.setManufacturerName(manufacturerDTO.getManufacturerName());
           manufacturer.setManufacturerCountry(manufacturerDTO.getManufacturerCountry());
           manufacturer.setDescription(manufacturerDTO.getDescription());
           ManufacturerDTO manufacturerDTOReturn = new ManufacturerDTO();
           BeanUtils.copyProperties(manufacturerRepository.save(manufacturer), manufacturerDTOReturn);
           return manufacturerDTOReturn;
       } catch (RuntimeException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public boolean deleteManufacturer(int manufacturerID) {
        try {
            manufacturerRepository.deleteManufacturerByManufacturerID(manufacturerID);
            return true;
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}

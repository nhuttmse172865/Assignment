package com.spring.carservice.services.Impl;

import com.spring.carservice.dtos.CarInformationDTO;
import com.spring.carservice.dtos.CustomerDTO;
import com.spring.carservice.dtos.ManufacturerDTO;
import com.spring.carservice.dtos.SupplierDTO;
import com.spring.carservice.pojos.CarInformation;
import com.spring.carservice.repositories.CarInformationRepository;
import com.spring.carservice.repositories.ManufacturerRepository;
import com.spring.carservice.repositories.SupplierRepository;
import com.spring.carservice.services.CarInformationService;
import com.spring.carservice.services.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarInformationServiceImpl implements CarInformationService {

    @Autowired
    private CarInformationRepository carInformationRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public CarInformationDTO getCarInformationById(int id) {
        try {
            CarInformation carInformation = carInformationRepository.getCarInformationByCarID(id);
            if(carInformation == null){
                return null;
            }
            CarInformationDTO carInformationDTO = new CarInformationDTO();
            BeanUtils.copyProperties(carInformation, carInformationDTO);
            return carInformationDTO;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CarInformationDTO> getCarsInformation() {
        try {
            List<CarInformation> carInformationList = carInformationRepository.findAll();
            List<CarInformationDTO> carInformationDTOList = new ArrayList<>();
            carInformationList.forEach(carInformation -> {
                CarInformationDTO carInformationDTO = new CarInformationDTO();
                BeanUtils.copyProperties(carInformation, carInformationDTO);
                if(carInformation.getManufacturer() != null){
                    ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
                    BeanUtils.copyProperties(carInformation.getManufacturer(), manufacturerDTO);
                    carInformationDTO.setManufacturerID(manufacturerDTO);
                }
                if(carInformation.getSupplier() != null){
                    SupplierDTO supplierDTO = new SupplierDTO();
                    BeanUtils.copyProperties(carInformation.getSupplier(), supplierDTO);
                    carInformationDTO.setSupplierID(supplierDTO);
                }
                carInformationDTOList.add(carInformationDTO);
            });
            return carInformationDTOList;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CarInformationDTO createCarInformation(CarInformationDTO carInformationDTO) {

        try {
            CustomerDTO customerDTO = customerService.getCustomer(carInformationDTO.getCustomerID());
            if(!customerDTO.getRole().equals("ADMIN")) throw new RuntimeException("Unauthorized");
            CarInformation carInformation = new CarInformation();
            carInformation.setCarName(carInformationDTO.getCarName());
            carInformation.setCarDescription(carInformationDTO.getCarDescription());
            carInformation.setNumberOfDoors(carInformationDTO.getNumberOfDoors());
            carInformation.setSeatingCapacity(carInformationDTO.getSeatingCapacity());
            carInformation.setFuelType(carInformationDTO.getFuelType());
            carInformation.setYear(carInformationDTO.getYear());
            carInformation.setCarStatus(carInformationDTO.getCarStatus());
            carInformation.setCarRentingPricePerDay(carInformationDTO.getCarRentingPricePerDay());
            carInformation.setManufacturer(manufacturerRepository.getManufacturerByManufacturerID(carInformationDTO.getManufacturerID().getManufacturerID()));
            carInformation.setSupplier(supplierRepository.getSuppliersBySupplierID(carInformationDTO.getSupplierID().getSupplierID()));
            carInformationRepository.save(carInformation);
            return carInformationDTO;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public CarInformationDTO updateCarInformation(int id, CarInformationDTO carInformationDTO) {
        try {
            CustomerDTO customerDTO = customerService.getCustomer(carInformationDTO.getCustomerID());
            if(!customerDTO.getRole().equals("ADMIN")) throw new RuntimeException("Unauthorized");
            CarInformation carInformation = carInformationRepository.getCarInformationByCarID(id);
            carInformation.setCarName(carInformationDTO.getCarName());
            carInformation.setCarDescription(carInformationDTO.getCarDescription());
            carInformation.setNumberOfDoors(carInformationDTO.getNumberOfDoors());
            carInformation.setSeatingCapacity(carInformationDTO.getSeatingCapacity());
            carInformation.setFuelType(carInformationDTO.getFuelType());
            carInformation.setYear(carInformationDTO.getYear());
            carInformation.setCarStatus(carInformationDTO.getCarStatus());
            carInformation.setCarRentingPricePerDay(carInformationDTO.getCarRentingPricePerDay());
           if(carInformationDTO.getManufacturerID() != null){
               carInformation.setManufacturer(manufacturerRepository.getManufacturerByManufacturerID(carInformationDTO.getManufacturerID().getManufacturerID()));
           }
         if(carInformationDTO.getSupplierID() != null){
             carInformation.setSupplier(supplierRepository.getSuppliersBySupplierID(carInformationDTO.getSupplierID().getSupplierID()));
         }
            carInformationRepository.save(carInformation);
            return carInformationDTO;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCarInformation(int id, int customerID) {
        try{
            CustomerDTO customerDTO = customerService.getCustomer(customerID);
            if(!customerDTO.getRole().equals("ADMIN")) throw new RuntimeException("Unauthorized");
            carInformationRepository.deleteById(id);
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateCarInformationStatus(int id, Byte status) {
       try{
           CarInformation carInformation = carInformationRepository.getCarInformationByCarID(id);
           carInformation.setCarStatus(status);
           carInformationRepository.save(carInformation);
           return true;
       }catch (RuntimeException e){
           return false;
       }
    }
}

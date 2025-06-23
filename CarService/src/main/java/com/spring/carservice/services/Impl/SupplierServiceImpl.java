package com.spring.carservice.services.Impl;

import com.spring.carservice.dtos.SupplierDTO;
import com.spring.carservice.pojos.Supplier;
import com.spring.carservice.repositories.SupplierRepository;
import com.spring.carservice.services.SupplierService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public SupplierDTO getSupplier(int supplierID) {
        try{
            Supplier supplier = supplierRepository.getSuppliersBySupplierID(supplierID);
            if(supplier == null) return null;
            SupplierDTO supplierDTO = new SupplierDTO();
            BeanUtils.copyProperties(supplier, supplierDTO);
            return supplierDTO;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SupplierDTO> getSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        List<SupplierDTO> supplierDTOs = new ArrayList<>();
        suppliers.forEach(supplier -> {
            SupplierDTO supplierDTO = new SupplierDTO();
            BeanUtils.copyProperties(supplier, supplierDTO);
            supplierDTOs.add(supplierDTO);
        });
        return supplierDTOs;
    }

    @Override
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        try{
            Supplier supplier = new Supplier();
            supplier.setSupplierName(supplierDTO.getSupplierName());
            supplier.setSupplierDescription(supplierDTO.getSupplierDescription());
            supplier.setSupplierAddress(supplierDTO.getSupplierAddress());
            SupplierDTO supplierDTOReturn = new SupplierDTO();
            BeanUtils.copyProperties(supplierRepository.save(supplier), supplierDTOReturn);
            return  supplierDTOReturn;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SupplierDTO updateSupplier(int supplierID, SupplierDTO supplierDTO) {
        try{
            Supplier supplier = supplierRepository.getSuppliersBySupplierID(supplierID);
            supplier.setSupplierName(supplierDTO.getSupplierName());
            supplier.setSupplierDescription(supplierDTO.getSupplierDescription());
            supplier.setSupplierAddress(supplierDTO.getSupplierAddress());
            SupplierDTO supplierDTOReturn = new SupplierDTO();
            BeanUtils.copyProperties(supplierRepository.save(supplier), supplierDTOReturn);
            return  supplierDTOReturn;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteSupplier(int supplierID) {
        try{
            supplierRepository.deleteById(supplierID);
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}

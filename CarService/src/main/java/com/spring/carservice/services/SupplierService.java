package com.spring.carservice.services;

import com.spring.carservice.dtos.SupplierDTO;
import com.spring.carservice.pojos.Supplier;

import java.util.List;

public interface SupplierService {

    public SupplierDTO getSupplier(int supplierID);
    public List<SupplierDTO> getSuppliers();
    public SupplierDTO createSupplier(SupplierDTO supplierDTO);
    public SupplierDTO updateSupplier(int supplierID, SupplierDTO supplierDTO);
    public boolean deleteSupplier(int supplierID);
}

package com.spring.carservice.configs;

import com.spring.carservice.dtos.ManufacturerDTO;
import com.spring.carservice.dtos.SupplierDTO;
import com.spring.carservice.services.ManufacturerService;
import com.spring.carservice.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Override
    public void run(String... args) throws Exception {
        generateSuppliers();
        generateManufacturers();
    }

    public void generateManufacturers(){
        if(!manufacturerService.getAllManufacturers().isEmpty()){
            return;
        }

        ManufacturerDTO manufacturerDTO1 = new ManufacturerDTO();
        manufacturerDTO1.setManufacturerName("Toyota");
        manufacturerDTO1.setManufacturerCountry("JPN");
        manufacturerDTO1.setDescription("Leading global automotive manufacturer known for reliability and hybrid technology.");
        manufacturerService.createManufacturer(manufacturerDTO1);

        ManufacturerDTO manufacturerDTO2 = new ManufacturerDTO();
        manufacturerDTO2.setManufacturerName("Ford");
        manufacturerDTO2.setManufacturerCountry("USA");
        manufacturerDTO2.setDescription("American multinational automaker that designs, manufactures, markets, and services a full line of Ford cars, trucks, SUVs, electric vehicles, and Lincoln luxury vehicles.");
        manufacturerService.createManufacturer(manufacturerDTO2);

        ManufacturerDTO manufacturerDTO3 = new ManufacturerDTO();
        manufacturerDTO3.setManufacturerName("Volkswagen");
        manufacturerDTO3.setManufacturerCountry("DEU");
        manufacturerDTO3.setDescription("German multinational automotive manufacturer known for its Beetle and Golf models, and its strong presence in the global market.");
        manufacturerService.createManufacturer(manufacturerDTO3);

        ManufacturerDTO manufacturerDTO4 = new ManufacturerDTO();
        manufacturerDTO4.setManufacturerName("Hyundai");
        manufacturerDTO4.setManufacturerCountry("KR");
        manufacturerDTO4.setDescription("South Korean multinational automotive manufacturer known for its diverse range of vehicles and commitment to electric mobility.");
        manufacturerService.createManufacturer(manufacturerDTO4);


        ManufacturerDTO manufacturerDTO5 = new ManufacturerDTO();
        manufacturerDTO5.setManufacturerName("Mercedes-Benz");
        manufacturerDTO5.setManufacturerCountry("DEU");
        manufacturerDTO5.setDescription("German luxury automotive brand known for its high-performance cars, sedans, and commercial vehicles.");
        manufacturerService.createManufacturer(manufacturerDTO5);
    }

    public void generateSuppliers() {
        if(!supplierService.getSuppliers().isEmpty()) {
            return;
        }

        SupplierDTO supplierDTO1 = new SupplierDTO();
        supplierDTO1.setSupplierName("Apex Auto Parts");
        supplierDTO1.setSupplierAddress("456 Engine Drive, Industrial Zone");
        supplierDTO1.setSupplierDescription("Wholesale distributor of genuine and aftermarket automotive components.");
        supplierService.createSupplier(supplierDTO1);

        SupplierDTO supplierDTO2 = new SupplierDTO();
        supplierDTO2.setSupplierName("Tire Tread Co.");
        supplierDTO2.setSupplierAddress("78 Rubber Road, Metro City");
        supplierDTO2.setSupplierDescription("Leading supplier of passenger, truck, and performance tires.");
        supplierService.createSupplier(supplierDTO2);

        SupplierDTO supplierDTO3 = new SupplierDTO();
        supplierDTO3.setSupplierName("Lubricant Link");
        supplierDTO3.setSupplierAddress("101 Fluid Lane, Chem Park");
        supplierDTO3.setSupplierDescription("Provider of engine oils, coolants, brake fluids, and detailing chemicals.");
        supplierService.createSupplier(supplierDTO3);

        SupplierDTO supplierDTO4 = new SupplierDTO();
        supplierDTO4.setSupplierName("ScanMaster Diagnostics");
        supplierDTO4.setSupplierAddress("22 Circuit St, Tech Hub");
        supplierDTO4.setSupplierDescription("Specializing in advanced automotive diagnostic tools and software.");
        supplierService.createSupplier(supplierDTO4);


        SupplierDTO supplierDTO5 = new SupplierDTO();
        supplierDTO5.setSupplierName("Garage Gear Solutions");
        supplierDTO5.setSupplierAddress("55 Workshop Way, Industrial Park");
        supplierDTO5.setSupplierDescription("Supplier of lifts, tools, and workshop equipment for automotive service centers.");
        supplierService.createSupplier(supplierDTO5);
    }
}

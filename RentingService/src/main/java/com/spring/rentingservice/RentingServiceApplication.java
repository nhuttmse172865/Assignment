package com.spring.rentingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients()
@SpringBootApplication
public class RentingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentingServiceApplication.class, args);
    }

}

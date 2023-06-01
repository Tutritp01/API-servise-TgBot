package com.tutrit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tutrit")
public class ApiServiceDistributive {
    public static void main(String[] args) {
        SpringApplication.run(ApiServiceDistributive.class, args);
    }
}


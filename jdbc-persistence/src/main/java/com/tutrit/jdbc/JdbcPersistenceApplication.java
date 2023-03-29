package com.tutrit.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ="com.tutrit")
public class JdbcPersistenceApplication {
    public static void main(String[] args) {
        SpringApplication.run(JdbcPersistenceApplication.class,args);
    }
}

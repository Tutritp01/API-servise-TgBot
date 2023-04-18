package com.tutrit.app;

import com.tutrit.jdbc.config.DataSourceConfig;
import com.tutrit.jdbc.repository.UserRepository;
import com.tutrit.jdbc.service.UserJDBCService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tutrit")
public class ApiServiceDistributive {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApiServiceDistributive.class, args);
        System.out.println(context.getBean(DataSourceConfig.class));
        System.out.println(context.getBean(UserJDBCService.class));
        System.out.println(context.getBean(UserRepository.class));

    }

}


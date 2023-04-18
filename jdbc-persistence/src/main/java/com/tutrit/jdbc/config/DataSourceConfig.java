package com.tutrit.jdbc.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
public class DataSourceConfig {
    private final Environment environment;

    public DataSourceConfig(final Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(environment.getProperty("jdbc.driverClassName", "com.mysql.cj.jdbc.Driver"))
                .url(environment.getProperty("jdbc.url", "jdbc:mysql://localhost:3306/url"))
                .username(environment.getProperty("jdbc.username", "username"))
                .password(environment.getProperty("jdbc.password", "password"))
                .build();
    }

}

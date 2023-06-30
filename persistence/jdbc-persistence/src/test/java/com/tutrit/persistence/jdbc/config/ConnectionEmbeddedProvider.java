package com.tutrit.persistence.jdbc.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class ConnectionEmbeddedProvider implements ConnectionInterfaces {

    @Value("${datasource.uri}")
    private String uri;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;
    @Value("${datasource.driver-class-name}")
    private String driverClassName;

    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(uri);
        System.out.println(uri);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


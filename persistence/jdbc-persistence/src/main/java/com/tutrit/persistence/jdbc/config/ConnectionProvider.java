package com.tutrit.persistence.jdbc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
public class ConnectionProvider {
    @Value("${datasource.uri}")
    private String uri;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;

    public Connection getConnection() {
        try{
            return DriverManager.getConnection(uri, username, password);
        } catch (Exception ex) {
            throw new RuntimeException("Error creating connection ");
        }
    }

}

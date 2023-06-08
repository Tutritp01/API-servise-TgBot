package com.tutrit.persistence.jdbc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
public class ConnectionProvider {
    @Value("${datasource.uri:jdbc:mysql://localhost:3306/sto}")
    private String uri = "jdbc:mysql://localhost:3306/sto";
    @Value("${datasource.username:root}")
    private String username = "root";
    @Value("${datasource.password:1234}")
    private String password = "1234";

    public Connection getConnection() {
        try{
            return DriverManager.getConnection(uri, username, password);
        } catch (Exception ex) {
            return null;
        }
    }

}

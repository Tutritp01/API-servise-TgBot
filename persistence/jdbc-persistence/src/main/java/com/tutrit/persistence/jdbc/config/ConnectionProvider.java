package com.tutrit.persistence.jdbc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



@Configuration
public class ConnectionProvider  {
    @Value("${datasource.uri:jdbc:mysql://localhost:3306/sto}")
    private String uri = "jdbc:mysql://localhost:3306/sto";
    @Value("${datasource.username:root}")
    private String username = "root";
    @Value("${datasource.password:1234}")
    private String password = "1234";

    /**
     * Obtain a database connection.
     *
     * @return A database connection object.
     * @throws SQLException If an error occurs while obtaining the connection.
     */
    public Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(uri, username, password);
        } catch (Exception ex) {
            return null;
        }
    }
}

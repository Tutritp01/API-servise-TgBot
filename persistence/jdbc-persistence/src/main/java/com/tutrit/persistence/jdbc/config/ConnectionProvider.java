package com.tutrit.persistence.jdbc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ConnectionProvider implements ConnectionInterfaces {
    @Value("${datasource.uri:jdbc:mysql://localhost:3306/sto}")
    private String uri;
    @Value("${datasource.username:root}")
    private String username;
    @Value("${datasource.password:1234}")
    private String password;
    @Value("${datasource.driver-class-name:com.mysql.cj.jdbc.Driver}")
    private String driverClassName;

    private Connection connection;

    /**
     * Obtain a database connection.
     *
     * @return A database connection object.
     * @throws SQLException If an error occurs while obtaining the connection.
     */
    @Override
    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(driverClassName);
                connection = DriverManager.getConnection(uri, username, password);
            } catch (Exception ex) {
                throw new RuntimeException("Error creating connection ");
            }
        }
        return connection;
    }
}

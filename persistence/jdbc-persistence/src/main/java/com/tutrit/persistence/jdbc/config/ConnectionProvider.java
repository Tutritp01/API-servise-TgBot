package com.tutrit.persistence.jdbc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



@Configuration
public class ConnectionProvider implements ConnectionInterfaces {
    @Value("${datasource.uri:jdbc:mysql://localhost:3306/db}")
    private String uri;
    @Value("${datasource.username:root}")
    private String username;
    @Value("${datasource.password:password}")
    private String password;
    @Value("${datasource.driver-class-name:com.mysql.cj.jdbc.Driver}")
    private String driverClassName;

    /**
     * Obtain a database connection.
     *
     * @return A database connection object.
     * @throws SQLException If an error occurs while obtaining the connection.
     */
    @Override
    public Connection getConnection() {

        try {
            Class.forName(driverClassName);
            return DriverManager.getConnection(uri, username, password);
        } catch (Exception ex) {
            throw new RuntimeException("Error creating connection ");
        }
    }


    /*public void createServer() throws SQLException {
        String JDBC_URL = "jdbc:h2:tcp://localhost/~/test"; // Change the database URL as per your requirement
        String USERNAME = "sa"; // H2 default username
        String PASSWORD = ""; // H2 default password
        try {
            // Start the H2 database server
            Server server = Server.createTcpServer("-tcpAllowOthers").start();
            System.out.println("H2 database server started.");

            // Establish the JDBC connection
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the H2 database.");

            // Perform database operations here

            // Stop the H2 database server
            server.stop();
            System.out.println("H2 database server stopped.");

        } catch (SQLException e) {
            System.out.println("Failed to connect to the H2 database: " + e.getMessage());
        }
    }*/

}

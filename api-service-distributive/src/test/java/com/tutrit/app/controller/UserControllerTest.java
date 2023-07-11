package com.tutrit.app.controller;

import com.tutrit.app.config.SpringContext;
import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.jdbc.config.ConnectionProvider;
import com.tutrit.persistence.jdbc.persistence.UserPersistenceJdbc;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringContext.SpringConfig.class)
class UserControllerTest {
    @LocalServerPort
    private int serverPort;
    private String id;
    private String name;
    private String phoneNumber;
    private User saveUser;
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `user` (`user_id` VARCHAR(255) PRIMARY KEY,`name` VARCHAR(255),`phone_number` VARCHAR(255))";
    @Autowired
    private ConnectionProvider connectionProvider;


    @BeforeEach
    public void setUp() {
        RestAssured.port = serverPort;
        saveUser = new User();
        saveUser.setName("James Wilson");
        saveUser.setPhoneNumber("111-111-1111");
        createTable(connectionProvider);
        response();
    }

    private void createTable(ConnectionProvider connectionProvider) {
        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.execute(CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void response() {
        Response response = given()
                .contentType("application/json")
                .body(saveUser)
                .when()
                .post("/users");

        id = response.then().extract().path("userId");
        name = response.then().extract().path("name");
        phoneNumber = response.then().extract().path("phoneNumber");
    }

    @Test
    void whenRequestPost_thenOK() {

        given()
                .then()
                .statusCode(200)
                .contentType("application/json");

        assertEquals(name, saveUser.getName());
        assertEquals(phoneNumber, saveUser.getPhoneNumber());
    }

    @Test
    void whenRequestGet_thenOK() {

        given()
                .when()
                .get("/users/{id}", id)
                .then()
                .statusCode(200)
                .contentType("application/json");

        assertEquals(name, saveUser.getName());
        assertEquals(phoneNumber, saveUser.getPhoneNumber());
    }

}

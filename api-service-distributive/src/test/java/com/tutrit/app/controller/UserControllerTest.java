package com.tutrit.app.controller;

import com.tutrit.app.config.SpringContext;
import com.tutrit.persistence.core.bean.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

    @BeforeEach
    public void setUp() {
        RestAssured.port = serverPort;
        saveUser = new User();
        saveUser.setName("James Wilson");
        saveUser.setPhoneNumber("111-111-1111");
        response();

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

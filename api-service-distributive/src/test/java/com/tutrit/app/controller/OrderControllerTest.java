package com.tutrit.app.controller;

import com.tutrit.app.ApiServiceDistributive;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.when;
@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {
    @LocalServerPort
    private int serverPort;

    @BeforeEach
    public void setUp() {
        RestAssured.port = serverPort;
    }

    @Test
    public void whenRequestGet_thenOK(){
        when().request("GET", "/orders").then()
                .log().body()
                .statusCode(200);
    }
}

package com.tutrit.springdata.config;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;

public class EntityManagerIT {
    public static final MySQLContainer<?> container = new MySQLContainer<>("mysql:8.0.33");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("hibernate.connection.url", container::getJdbcUrl);
    }
    @BeforeAll
    static void runContainer() {
        container.start();
    }
}

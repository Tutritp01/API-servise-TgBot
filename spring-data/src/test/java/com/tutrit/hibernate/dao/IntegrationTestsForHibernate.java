package com.tutrit.hibernate.dao;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MySQLContainer;
@TestPropertySource("classpath:hibernate-it.properties")
public class IntegrationTestsForHibernate {
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

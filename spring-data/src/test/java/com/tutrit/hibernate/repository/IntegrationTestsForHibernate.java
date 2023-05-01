package com.tutrit.hibernate.repository;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

public class IntegrationTestsForHibernate {
    public static final MySQLContainer<?> container = new MySQLContainer<>("mysql:8.0.33");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }
    @BeforeAll
    static void runContainer() {
        container.start();
    }
}

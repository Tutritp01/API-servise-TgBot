package com.tutrit.jdbc.config;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;

@Sql("classpath:create-tables.sql")
@ActiveProfiles("IT")
public class JdbcIT {

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

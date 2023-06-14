package com.tutrit.persistence.jdbc.template.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
/**
 * Provides configuration for JDBC template and
 * data source used for database connectivity.
 * This class is designed for extension and customization.
 */
@Configuration
public class ConnectionProviderJdbcTemplate {
    @Value("${datasource.uri}")
    private String uri;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;
    @Value("${datasource.driver-class-name}")
    private String driver;

    /**
     * Configures and provides the data source for database connectivity.
     *
     * @return the configured data source
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManager = new DriverManagerDataSource();
        driverManager.setUrl(uri);
        driverManager.setUsername(username);
        driverManager.setPassword(password);
        driverManager.setDriverClassName(driver);
        return driverManager;
    }
    /**
     * Configures and provides a customized JdbcTemplate
     * instance for database operations.
     * This method can be safely overridden in a subclass
     * to customize the JdbcTemplate behavior.
     *
     * @return the configured JdbcTemplate instance
     */
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

}

package com.tutrit.jdbc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JdbcConfiguration {
    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.driverClassName}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {

        DataSourceBuilder<?> sourceBuilder = DataSourceBuilder.create();
        sourceBuilder.url(url);
        sourceBuilder.password(password);
        sourceBuilder.username(username);
        sourceBuilder.driverClassName(driverClassName);

        return sourceBuilder.build();
    }

}

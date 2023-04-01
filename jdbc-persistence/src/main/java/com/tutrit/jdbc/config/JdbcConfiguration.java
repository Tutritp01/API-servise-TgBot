package com.tutrit.jdbc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcConfiguration {
    @Value("${db.url:jdbc:mysql://localhost:3306/default_db}")
    private String url;

    @Value("${db.username:root}")
    private String username;

    @Value("${db.password:password}")
    private String password;

    @Value("${db.driverClassName:com.mysql.cj.jdbc.Driver}")
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

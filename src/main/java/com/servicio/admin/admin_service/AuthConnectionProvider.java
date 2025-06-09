package com.servicio.admin.admin_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

@Component
public class AuthConnectionProvider {
    
    @Value("${spring.datasource.url}")
    private String databaseUrl;
    
    @Value("${spring.datasource.username}")
    private String databaseUsername;
    
    @Value("${spring.datasource.password}")
    private String databasePassword;
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            databaseUrl,
            databaseUsername,
            databasePassword
        );
    }
}
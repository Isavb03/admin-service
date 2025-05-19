package com.servicio.admin.admin_service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

@Component
public class AuthConnectionProvider {
    
    @Value("${spring.datasource.url}")
    private String url;
    
    @Value("${spring.datasource.username}")
    private String username;
    
    @Value("${spring.datasource.password}")
    private String password;
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
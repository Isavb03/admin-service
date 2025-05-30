package com.servicio.admin.admin_service;

import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

@Component
public class AuthConnectionProvider {
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://mysql-service:3306/university",
            "admin",
            "password"
        );
    }
}
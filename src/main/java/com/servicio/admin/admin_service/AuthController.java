package com.servicio.admin.admin_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @Value("${admin.username:admin}")
    private String configUsername;
    
    @Value("${admin.password:1234}")
    private String configPassword;

    // Simple ping endpoint to verify the controller is registered
    @GetMapping("/ping")
    public String ping() {
        logger.info("Ping endpoint accessed");
        return "pong";
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        logger.info("Health check endpoint accessed");
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "admin-service");
        response.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        
        logger.info("Login attempt received for username: {}", username);
        
        Map<String, String> response = new HashMap<>();
        
        // Log the request parameters for debugging
        logger.debug("Username provided: {}", username);
        logger.debug("Expected username: {}", configUsername);
        logger.debug("Password match: {}", configPassword.equals(password));
        
        if (configUsername.equalsIgnoreCase(username) && configPassword.equals(password)) {
            logger.info("Login successful for user: {}", username);
            response.put("status", "success");
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } else {
            logger.warn("Login failed for user: {}", username);
            response.put("status", "error");
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
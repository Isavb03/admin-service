package com.servicio.admin.admin_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @Value("${admin.username:admin}")
    private String configUsername;
    
    @Value("${admin.password:1234}")
    private String configPassword;

    // Health check endpoint that can be used to verify the service is running
    @GetMapping("/health")
    public ResponseEntity<?> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, 
                                  @RequestParam String password) {
        logger.info("Login attempt: username={}", username);
        
        // Log configured values (but don't log the actual password)
        logger.debug("Configured username: {}", configUsername);
        logger.debug("Password match: {}", configPassword.equals(password));
        
        if(configUsername.equalsIgnoreCase(username) && configPassword.equals(password)) {
            logger.info("Login successful for user: {}", username);
            return ResponseEntity.ok().build();
        }
        
        logger.warn("Login failed for user: {}", username);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
package com.servicio.admin.admin_service;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;  // Added import for HttpStatus
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") // Important for cross-origin requests
public class AuthController {
    
    @Value("${admin.username:admin}")
    private String adminUsername;
    
    @Value("${admin.password:1234}")
    private String adminPassword;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, 
                                  @RequestParam String password) {
        // Reutilización directa de tu lógica actual
        if("admin".equalsIgnoreCase(username) && "1234".equals(password)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
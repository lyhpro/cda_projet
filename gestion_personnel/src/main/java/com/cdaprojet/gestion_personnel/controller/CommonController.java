package com.cdaprojet.gestion_personnel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gestionnaire-personnel/common")
public class CommonController {
    
    @GetMapping("/hello")
    public ResponseEntity<String> helloCommon() {
        return ResponseEntity.ok("Hello Common");
    }

}

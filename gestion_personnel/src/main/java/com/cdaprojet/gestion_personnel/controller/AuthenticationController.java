package com.cdaprojet.gestion_personnel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdaprojet.gestion_personnel.model.JwtResponse;
import com.cdaprojet.gestion_personnel.model.SigninRequest;
import com.cdaprojet.gestion_personnel.service.signinrequest.SigninRequestService;

@RestController
@RequestMapping("/gestionnaire-personnel/authentication")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthenticationController {
    
    @Autowired
    private SigninRequestService signinRequestService;

    @GetMapping("/hello")
    public ResponseEntity<String> helloAuthentication() {
        return ResponseEntity.ok("Hello Authentication");
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signin(@RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok(signinRequestService.signin(signinRequest));
    }

    @GetMapping("/activatedUser/{token}")
    public ResponseEntity<Boolean> activatedUser(@PathVariable String token) {
        return ResponseEntity.ok(signinRequestService.activatedUser(token));
    }

    @GetMapping("/updatePwdUser/{token}")
    public ResponseEntity<Boolean> updatePasswordUser(@PathVariable String token) {
        return ResponseEntity.ok(signinRequestService.updatePasswordUser(token));
    }

}

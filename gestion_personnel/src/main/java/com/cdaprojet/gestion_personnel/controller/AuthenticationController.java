package com.cdaprojet.gestion_personnel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdaprojet.gestion_personnel.model.JwtResponse;
import com.cdaprojet.gestion_personnel.model.SigninRequest;
import com.cdaprojet.gestion_personnel.model.SignupRequest;
import com.cdaprojet.gestion_personnel.model.user.UserDto;
import com.cdaprojet.gestion_personnel.service.signinrequest.SigninRequestService;
import com.cdaprojet.gestion_personnel.service.signuprequest.SignupRequestService;

@RestController
@RequestMapping("/gestionnaire-personnel/authentication")
public class AuthenticationController {
    
    @Autowired
    private SigninRequestService signinRequestService;

    @Autowired
    private SignupRequestService signupRequestService;

    @GetMapping("/hello")
    public ResponseEntity<String> helloAuthentication() {
        return ResponseEntity.ok("Hello Authentication");
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signin(@RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok(signinRequestService.signin(signinRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(signupRequestService.signup(signupRequest));
    }

}

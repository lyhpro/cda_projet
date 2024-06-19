package com.cdaprojet.gestion_personnel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdaprojet.gestion_personnel.model.SignupRequest;
import com.cdaprojet.gestion_personnel.model.role.Role;
import com.cdaprojet.gestion_personnel.model.user.UserDto;
import com.cdaprojet.gestion_personnel.service.role.RoleService;
import com.cdaprojet.gestion_personnel.service.signuprequest.SignupRequestService;
import com.cdaprojet.gestion_personnel.service.user.UserService;

@RestController
@RequestMapping("/gestionnaire-personnel/admin")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AdminController {
    
    @Autowired
    private SignupRequestService signupRequestService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/hello")
    public ResponseEntity<String> helloAdmin() {
        return ResponseEntity.ok("Hello Admin");
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequest signupRequest) {
        System.out.println("OHEOHEOHEOHE");
        return ResponseEntity.ok(signupRequestService.signup(signupRequest));
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDto>> getAllUserExceptAdmin(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/getAllRole")
    public ResponseEntity<List<Role>> getAllRoleExceptAdmin(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(roleService.getAllRole());
    }
}
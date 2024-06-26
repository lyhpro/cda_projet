package com.cdaprojet.gestion_personnel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdaprojet.gestion_personnel.model.pathModel.path.PathDto;
import com.cdaprojet.gestion_personnel.model.user.UserDto;
import com.cdaprojet.gestion_personnel.service.path.PathService;
import com.cdaprojet.gestion_personnel.service.user.UserService;

@RestController
@RequestMapping("/gestionnaire-personnel/common")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CommonController {

    @Autowired
    private UserService userService;

    @Autowired
    private PathService pathService;
    
    @GetMapping("/hello")
    public ResponseEntity<String> helloCommon() {
        return ResponseEntity.ok("Hello Common");
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserDto> getUser(@RequestHeader(name = "Authorization") String jwt) {
        return ResponseEntity.ok(userService.getUser(jwt));
    }

    @GetMapping("/loadMenu/byRoleName/{roleName}")
    public ResponseEntity<List<PathDto>> getRoutesByNomRole(@PathVariable String roleName) {
        return ResponseEntity.ok(pathService.getPathsByRoleName(roleName));
    }

}

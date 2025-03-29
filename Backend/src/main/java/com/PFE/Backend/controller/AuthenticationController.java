package com.PFE.Backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.PFE.Backend.models.AuthenticationResponse;
import com.PFE.Backend.models.User;
import com.PFE.Backend.service.AuthenticationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins="http://localhost:3002/")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse>register(
        @RequestBody User request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
        @RequestBody User request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


   


}

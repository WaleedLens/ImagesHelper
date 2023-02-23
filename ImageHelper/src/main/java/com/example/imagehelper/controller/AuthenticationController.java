package com.example.imagehelper.controller;

import com.example.imagehelper.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final TokenService tokenService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/login")
    public String loginUser(Authentication authentication){
        if(authentication.isAuthenticated()) {
            log.debug("TOKEN requested for user '{}'", authentication.getName());
            System.out.println("I found you!");
            String token = tokenService.generateToken(authentication);

            log.debug("Your token has been generated successfully ! '{}'", token);
            return token;
        }else{return null;}


    }






}

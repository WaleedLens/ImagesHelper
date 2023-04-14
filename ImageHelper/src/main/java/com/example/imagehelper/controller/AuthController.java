package com.example.imagehelper.controller;

import com.example.imagehelper.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The aim of this class is to create JWT tokens
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final TokenService tokenService;

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * If Authentication is succeeded then generate JWT token
     * @param authentication
     * @return JWT token/Bearer Token
     */
    @GetMapping("/login")
    public String loginUser(Authentication authentication){
        if(authentication.isAuthenticated()) {
            log.debug("TOKEN requested for user '{}'", authentication.getName());
            String token = tokenService.generateToken(authentication); //Generate token

            log.debug("Your token has been generated successfully ! '{}'", token);
            return token;
        }else{return null;}


    }






}

package com.example.imagehelper.auth;

import com.example.imagehelper.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

public class AuthenticationProvidedHelper implements AuthenticationProvider {

    private final UserService userService;


    public AuthenticationProvidedHelper(UserService userService) {
        this.userService = userService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        System.out.println(validateCredentials(authentication.getCredentials().toString(),userService.loadUserByUsername(authentication.getPrincipal().toString()).getPassword()));
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }


    public boolean validateCredentials(String providedPassword, String userPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("Provided Password: " + providedPassword);
            System.out.println("User Password: " + userPassword);
        System.out.println(encoder.encode(providedPassword));
        return encoder.matches(providedPassword,(userPassword));
    }




}

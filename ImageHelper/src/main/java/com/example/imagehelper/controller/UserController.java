package com.example.imagehelper.controller;

import com.example.imagehelper.model.User;
import com.example.imagehelper.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user){
        LOG.debug("New Register Request");
        userService.registerUser(user);
        return ResponseEntity.ok().build();
    }
}

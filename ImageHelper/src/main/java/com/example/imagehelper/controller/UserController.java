package com.example.imagehelper.controller;

import com.example.imagehelper.model.User;
import com.example.imagehelper.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.registerUser(user);
        return ResponseEntity.ok().build();
    }


    /**
     * TODO: We need to initiate smtp.
     */

    /**
     * Update password of given username.
     *
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return
     */

    @PostMapping("/updatepassword")
    public ResponseEntity updatePassword(@RequestParam String username,@RequestParam String oldPassword,@RequestParam String newPassword){
        if(userService.updatePassword(oldPassword,username,newPassword)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/password/confirmation")
    public ResponseEntity passwordEmailConfirmation(){
        return null;
    }


}

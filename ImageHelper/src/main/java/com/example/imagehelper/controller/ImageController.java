package com.example.imagehelper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/image/")
public class ImageController {


    @GetMapping("/getImage")
    public ResponseEntity getImage(@RequestBody String imagepointer) {
        return null;
    }

    @GetMapping("/findImagesByDate")
    public ResponseEntity findImagesByDate(@RequestBody String stringDate, Authentication authentication) {
        System.out.println(authentication.getName());
        return null;
    }

}

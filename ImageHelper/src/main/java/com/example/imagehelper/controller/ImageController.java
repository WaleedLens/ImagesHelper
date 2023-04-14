package com.example.imagehelper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The aim of this class is getImages stored in database by number of ways:
 * 1- By date:get images between two dates (given in a string)
 * 2- get Image of a given Image-Pointer.
 */
@RestController
@RequestMapping("/api/image/")
public class ImageController {


    @GetMapping("/getImage")
    public ResponseEntity getImage(@RequestBody String imagePointer) {
        return null;
    }

    /**
     * Receives Date in string format of two dates, and return List of images in given interval(two dates) to a given user.
     * @param stringDate
     * @param authentication <- To extract Authenticated user information
     * @return
     */
    @GetMapping("/findImagesByDate")
    public ResponseEntity findImagesByDate(@RequestBody String stringDate,Authentication authentication) {

        return null;
    }

}

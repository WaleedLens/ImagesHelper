package com.example.imagehelper.controller;

import com.example.imagehelper.repository.UserRepository;
import com.example.imagehelper.service.ImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/api/images/upload")
public class ImageUploadController {
    private final ImageUploadService imageUploadService;

    @Autowired
    public ImageUploadController(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;


    }


    /**
     * Rest endpoint takes image from end-user and save that image in db server
     * @param singleImage
     * @return
     */
    @PostMapping("/single")
    public ResponseEntity uploadSingleImage(@RequestParam("images")MultipartFile singleImage){
        try {

            imageUploadService.uploadSingleImage(singleImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/multi")
    public ResponseEntity uploadMultiImages(@RequestParam("images")MultipartFile[] multiImages){
        try {
            imageUploadService.uploadMultiImages(multiImages);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/avatar")
    public ResponseEntity uploadAvatar(@RequestParam("avatar") MultipartFile avatarImage){
        try {
            imageUploadService.uploadAvatar(avatarImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }






}

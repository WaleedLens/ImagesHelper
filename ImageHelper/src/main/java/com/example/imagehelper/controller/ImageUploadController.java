package com.example.imagehelper.controller;

import com.example.imagehelper.core.utils.PatternUtils;
import com.example.imagehelper.service.ImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * The aim of this class is to upload images from user to the server
 * User can upload single of multiple images to the server.
 */
@RestController
@Slf4j
@RequestMapping("/api/image/upload")
public class ImageUploadController {
    private final ImageUploadService imageUploadService;

    @Autowired
    public ImageUploadController(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;


    }


    /**
     * Receives Single image and upload it to the server/database.
     *
     * @param singleImage Single Image from end-user
     * @param auth <- Authenticated User information could be extracted from this argument
     * @return
     */
    @PostMapping("/single")
    public ResponseEntity uploadSingleImage(@RequestParam("images") MultipartFile singleImage,@RequestHeader(value="Authorization") String auth) {

        imageUploadService.uploadSingleImage(singleImage, PatternUtils.extractUsername(auth));
        return ResponseEntity.ok().build();
    }

    /**
     * Receives Array of images and upload those images to the server.
     * @param multiImages
     * @param auth <- Authenticated User information could be extracted from this argument
     * @return
     */
    @PostMapping("/multi")
    public ResponseEntity uploadMultiImages(@RequestParam("images") MultipartFile[] multiImages,@RequestHeader("Authorization") String auth) {

        imageUploadService.uploadMultiImages(multiImages,PatternUtils.extractUsername(auth));

        return ResponseEntity.ok().build();
    }

    /**
     * Receives single image(avatar) upload it to the server
     * @param avatarImage
     * @param auth <- Authenticated User information could be extracted from this argument
     * @return
     */
    @PostMapping("/avatar")
    public ResponseEntity uploadAvatar(@RequestParam("avatar") MultipartFile avatarImage,@RequestHeader("Authorization") String auth) {

        imageUploadService.uploadAvatar(avatarImage,PatternUtils.extractUsername(auth));

        return ResponseEntity.ok().build();
    }




}

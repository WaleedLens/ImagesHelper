package com.example.imagehelper.controller;

import com.example.imagehelper.service.ImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
     * Take Thumbnail and save it in database
     *
     * @param singleImage Single Image from end-user
     * @return
     */
    @PostMapping("/single")
    public ResponseEntity uploadSingleImage(@RequestParam("images") MultipartFile singleImage) {

        imageUploadService.uploadSingleImage(singleImage);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/multi")
    public ResponseEntity uploadMultiImages(@RequestParam("images") MultipartFile[] multiImages) {

        imageUploadService.uploadMultiImages(multiImages);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/avatar")
    public ResponseEntity uploadAvatar(@RequestParam("avatar") MultipartFile avatarImage) {

        imageUploadService.uploadAvatar(avatarImage);

        return ResponseEntity.ok().build();
    }




}

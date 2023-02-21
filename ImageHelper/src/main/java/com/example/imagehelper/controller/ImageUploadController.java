package com.example.imagehelper.controller;

import com.example.imagehelper.service.ImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

        return null;
    }

    @PostMapping("/multi")
    public ResponseEntity uploadMultiImages(@RequestParam("images")MultipartFile multiImages){
        return null;
    }






}

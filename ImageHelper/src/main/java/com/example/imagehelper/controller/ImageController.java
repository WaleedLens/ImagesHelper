package com.example.imagehelper.controller;

import com.example.imagehelper.core.utils.PatternUtils;
import com.example.imagehelper.model.Image;
import com.example.imagehelper.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The aim of this class is getImages stored in database by number of ways:
 * 1- By date:get images between two dates (given in a string)
 * 2- get Image of a given Image-Pointer.
 */
@RestController
@RequestMapping("/api/image/")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Returns image metadata of given image pointer
     *
     * @param imagePointer
     * @return
     */
    @GetMapping("/getImage")
    public String getImage(@RequestBody String imagePointer, @RequestHeader("Authorization") String token) {

        return imageService.getImage(imagePointer, PatternUtils.extractUsername(token)).toString();
    }
    /**
     * Receives Date in string format of two dates, and return List of images in given interval(two dates) to a given user.
     *
     * @param stringDate
     * @param token      <- To extract Authenticated user information(e.g., username ).
     * @return
     */
    @GetMapping("/findImagesByDate")
    public List<Image> findImagesByDate(@RequestBody String stringDate, @RequestHeader("Authorization") String token) {
        return imageService.searchImagesByDate(stringDate, PatternUtils.extractUsername(token));
    }

}

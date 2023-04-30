package com.example.imagehelper.service;

import com.example.imagehelper.model.Image;

import java.util.List;

public interface ImageService {


    /**
     * Search for images in given date bounded
     * @param date (i.e., 2019/5/14:2023/1/2) Translates into -> from 2019/5/14 to 2023/1/2
     * @return List of images
     */
    List<Image> searchImagesByDate(String date,String username);

    /**
     * Get image
     * @param imagePointer
     * @return an image
     */
    Image getImage(String imagePointer,String username);



}

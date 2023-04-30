package com.example.imagehelper.service.impl;

import com.example.imagehelper.model.Image;
import com.example.imagehelper.repository.ImageRepository;
import com.example.imagehelper.service.ImageService;

import java.util.List;

public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    @Override
    public List<Image> searchImagesByDate(String date,String username) {

        return null;
    }

    @Override
    public Image getImage(String imagePointer,String username) {

        return null;
    }
}

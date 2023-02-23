package com.example.imagehelper.service.impl;

import com.example.imagehelper.model.Image;
import com.example.imagehelper.service.ImageService;

import java.util.List;

public class ImageServiceImpl implements ImageService {
    @Override
    public boolean deleteImage(String imagePointer) {
        return false;
    }

    @Override
    public List<Image> searchImagesByDate(String date) {
        return null;
    }

    @Override
    public Image getImage(String imagePointer) {
        return null;
    }
}

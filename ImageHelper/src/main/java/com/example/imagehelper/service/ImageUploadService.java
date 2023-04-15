package com.example.imagehelper.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface ImageUploadService {

    void uploadSingleImage(MultipartFile singleThumbnail, String albumName);

    void uploadMultiImages(MultipartFile[] multiImages,String albumName);

    void uploadAvatar(MultipartFile avatarImage, String albumName);
}

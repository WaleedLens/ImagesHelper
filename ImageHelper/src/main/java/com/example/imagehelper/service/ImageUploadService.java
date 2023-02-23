package com.example.imagehelper.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface ImageUploadService {

    void uploadSingleImage(MultipartFile singleThumbnail);

    void uploadMultiImages(MultipartFile[] multiImages);

    void uploadAvatar(MultipartFile avatarImage);
}

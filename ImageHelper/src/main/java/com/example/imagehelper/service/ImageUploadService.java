package com.example.imagehelper.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface ImageUploadService {

    void uploadSingleImage(MultipartFile singleThumbnail) throws IOException;

    void uploadMultiImages(MultipartFile[] multiImages) throws IOException;

    void uploadAvatar(MultipartFile avatarImage) throws IOException;
}

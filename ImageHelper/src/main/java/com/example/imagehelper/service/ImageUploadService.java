package com.example.imagehelper.service;

import java.io.File;
import java.io.InputStream;

public interface ImageUploadService {

    void uploadSingleImage(File file);

    void uploadMultiImages();
}

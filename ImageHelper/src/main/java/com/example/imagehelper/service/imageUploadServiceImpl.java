package com.example.imagehelper.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class imageUploadServiceImpl implements ImageUploadService{




    @Override
    public void uploadSingleImage(File file) {

    }

    @Override
    public void uploadMultiImages() {
        //userRepository.save(new User("User","name","codeavatar26631154"));
        System.out.println("Saved");

    }
}

package com.example.imagehelper.service;

import com.example.imagehelper.model.User;
import com.example.imagehelper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {
    private final UserRepository userRepository;

    @Autowired
    public ImageUploadServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void uploadSingleImage(File file) {

    }

    @Override
    public void uploadMultiImages() {
        userRepository.save(new User("User","name","codeavatar26631154"));
        System.out.println("Saved");

    }
}

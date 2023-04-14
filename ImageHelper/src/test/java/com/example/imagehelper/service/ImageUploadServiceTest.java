package com.example.imagehelper.service;

import com.example.imagehelper.core.utils.ImageTypes;
import com.example.imagehelper.model.Image;
import com.example.imagehelper.model.User;
import com.example.imagehelper.repository.ImageRepository;
import com.example.imagehelper.repository.UserRepository;
import com.example.imagehelper.service.impl.ImageUploadServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ImageUploadServiceTest {

    @Test
    void uploadSingleImageTest(){
        UserRepository userRepository = mock(UserRepository.class);
        ImageRepository imageRepository = mock(ImageRepository.class);
        ImageUploadService imageUploadService = new ImageUploadServiceImpl(userRepository,imageRepository);
        User user = new User("firstname","lastname","password","username","metatarsus.ping");
        user.setId(1);
        Image img = new Image("pointerTest",1, ImageTypes.THUMBNAIL);

        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));




    }
}

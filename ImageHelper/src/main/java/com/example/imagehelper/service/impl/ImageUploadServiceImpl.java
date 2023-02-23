package com.example.imagehelper.service.impl;

import com.example.imagehelper.core.utils.FileUtils;
import com.example.imagehelper.core.utils.ImageTypes;
import com.example.imagehelper.model.Image;
import com.example.imagehelper.model.User;
import com.example.imagehelper.repository.ImageRepository;
import com.example.imagehelper.repository.UserRepository;
import com.example.imagehelper.service.ImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ImageUploadServiceImpl implements ImageUploadService {
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public ImageUploadServiceImpl(UserRepository userRepository, ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;

        // userRepository.save(new User("foo","boo","dard","default.png"));
    }

    /**
     * Take image and save it in thumbnail directory
     *
     * @param singleThumbnail
     */
    @Override
    public void uploadSingleImage(MultipartFile singleThumbnail){
        try {
            uploadImage(singleThumbnail,ImageTypes.THUMBNAIL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void uploadMultiImages(MultipartFile[] multiImages)  {
        for (MultipartFile singleImage : multiImages) {
            try {
                uploadImage(singleImage,ImageTypes.THUMBNAIL);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void uploadAvatar(MultipartFile avatarImage) {
        try {
            uploadImage(avatarImage,ImageTypes.AVATAR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    public void uploadImage(MultipartFile image,ImageTypes type) throws IOException {
        String uniquePointer = FileUtils.uniqueTokenPointer(image.getOriginalFilename()); //Get unique pointer

        String description = image.getContentType() + ":" + image.getOriginalFilename() + ":" + image.getSize(); //Get detail description of image

        User user = userRepository.getUserByUsername("root"); //Get User information
        int id = user.getId();
        String imagePath = FileUtils.getResourcesPath() + "thumbnails/" + uniquePointer + FileUtils.getExtension(image.getOriginalFilename()); //Init path
        FileUtils.saveImage(image.getBytes(), imagePath); //Save image in given path
        imageRepository.save(new Image(uniquePointer, id, type)); //Save image pointer in db
        log.info("new image uploaded & saved in db successfully!-userID:" + id + " Description:" + description);
    }
}

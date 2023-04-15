package com.example.imagehelper.service.impl;

import com.example.imagehelper.core.utils.FileUtils;
import com.example.imagehelper.core.utils.ImageTypes;
import com.example.imagehelper.model.Image;
import com.example.imagehelper.model.User;
import com.example.imagehelper.repository.AlbumRepository;
import com.example.imagehelper.repository.ImageRepository;
import com.example.imagehelper.repository.UserRepository;
import com.example.imagehelper.service.ImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ImageUploadServiceImpl implements ImageUploadService {
    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;


    @Autowired
    public ImageUploadServiceImpl(UserRepository userRepository, ImageRepository imageRepository,
                                  AlbumRepository albumRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.albumRepository = albumRepository;
    }

    /**
     * Take image and save it in thumbnail directory
     * @param singleThumbnail
     */
    @Override
    public void uploadSingleImage(MultipartFile singleThumbnail,String albumName){
        try {
            uploadImage(singleThumbnail,ImageTypes.THUMBNAIL,albumName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    /**
     * Instruct uploadImage to upload  multi images <--- Check this later?
     * @param multiImages
     */
    @Override
    public void uploadMultiImages(MultipartFile[] multiImages,String albumName)  {
        for (MultipartFile singleImage : multiImages) {
            try {
                uploadImage(singleImage,ImageTypes.THUMBNAIL,albumName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Instruct uploadImage to upload  avatar
     * @param avatarImage
     */
    @Override
    public void uploadAvatar(MultipartFile avatarImage, String albumName) {
        try {
            uploadImage(avatarImage,ImageTypes.AVATAR,albumName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Upload given image in database and store that image in file based on type (avatar/thumbnail)
     * @param image This should be multipart File coming from end-user (Received by REST endpoint)
     * @param type This can be thumbnail or avatar ( see ImageType enum )
     * @param albumName <- Save image in given album name. [Note: Avatars would be saved in default album of a user]
     * @throws IOException
     */
    public void uploadImage(MultipartFile image,ImageTypes type,String albumName) throws IOException {
        String uniquePointer = FileUtils.generateHash(image.getOriginalFilename()); //Get unique pointer

        String description = image.getContentType() + ":" + image.getOriginalFilename() + ":" + image.getSize(); //Get detail description of image


        String imagePath = FileUtils.getResourcesPath() + "/thumbnails/" + uniquePointer + FileUtils.getExtension(image.getOriginalFilename()); //Init path
        FileUtils.saveFile(image.getBytes(), imagePath); //Save image in given path
        int albumId = albumRepository.getAlbumByName(albumName).getId();
        imageRepository.save(new Image(uniquePointer, albumId, type)); //Save image pointer in db
        log.info("new image uploaded & saved in db successfully!-Album ID:" + albumId + " Description:" + description);
    }
}

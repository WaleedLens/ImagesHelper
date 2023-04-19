package com.example.imagehelper.service.impl;

import com.example.imagehelper.core.utils.FileUtils;
import com.example.imagehelper.core.utils.ImageTypes;
import com.example.imagehelper.core.utils.PatternUtils;
import com.example.imagehelper.model.Album;
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
     *
     * @param singleThumbnail
     */
    @Override
    public void uploadSingleImage(MultipartFile singleThumbnail, String albumName) {
        try {
            uploadImage(singleThumbnail, ImageTypes.THUMBNAIL, albumName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Instruct uploadImage to upload  multi images <--- Check this later?
     *
     * @param multiImages
     */
    @Override
    public void uploadMultiImages(MultipartFile[] multiImages, String albumName) {
        for (MultipartFile singleImage : multiImages) {
            try {
                uploadImage(singleImage, ImageTypes.THUMBNAIL, albumName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Instruct uploadImage to upload  avatar
     *
     * @param avatarImage
     */
    @Override
    public void uploadAvatar(MultipartFile avatarImage, String albumName) {
        try {
            User user = userRepository.getUserByUsername(PatternUtils.extractUsernameFromAlbum(albumName));
            uploadImage(avatarImage, ImageTypes.AVATAR, albumName); //First upload the avatar and update current user Avatar pointer.


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method removes Current avatar "Physically".
     *
     * @param avatarPointer
     */
    private void removeCurrentAvatar(String avatarPointer) {
        FileUtils.removeAvatar(avatarPointer);
    }


    /**
     * Upload given image in database and store that image in file based on type (avatar/thumbnail)
     *
     * @param image     This should be multipart File coming from end-user (Received by REST endpoint)
     * @param type      This can be thumbnail or avatar ( see ImageType enum )
     * @param albumName <- Save image in given album name. [Note: Avatars would be saved in default album of a user]
     * @throws IOException
     */
    public void uploadImage(MultipartFile image, ImageTypes type, String albumName) throws IOException {

        String pointer = FileUtils.generateHash(image.getOriginalFilename()); //Get unique pointer

        String description = image.getContentType() + ":" + image.getOriginalFilename() + ":" + image.getSize(); //Get detail description of image


        String imagePath = FileUtils.getResourcesPath() + "/thumbnails/" + pointer + FileUtils.getExtension(image.getOriginalFilename()); //Init path
        FileUtils.saveFile(image.getBytes(), imagePath); //Save image in given path
        Album album = albumRepository.getAlbumByName(albumName);
        int albumId = albumRepository.getAlbumByName(albumName).getId();
        imageRepository.save(new Image(pointer, albumId, type)); //Save image pointer in db

        if (type.equals(ImageTypes.AVATAR)) {
            User user = userRepository.findById(album.getUserId()).get();
            String oldAvatarPointer = user.getAvatarPointer();

            // If current avatar image of a user is not the default one, then it should be removed from server's resource dir
            if (!oldAvatarPointer.equals("default.png")) {
                removeCurrentAvatar(oldAvatarPointer);
            }

            user.setAvatarPointer(pointer);
            userRepository.save(user);

            log.info("AvatarPointer of " + user.getUsername() + " has been updated successfully!");

        }
        log.info("new image uploaded & saved in db successfully!-Album ID:" + albumId + " Description:" + description);
    }
}

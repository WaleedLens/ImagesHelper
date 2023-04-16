package com.example.imagehelper.service;


import com.example.imagehelper.model.Image;
import com.example.imagehelper.repository.AlbumRepository;
import com.example.imagehelper.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class handles album operations (CRUD)
 */
@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final ImageRepository imageRepository;
    public AlbumService(AlbumRepository albumRepository,ImageRepository imageRepository){
        this.albumRepository = albumRepository;
        this.imageRepository = imageRepository;
    }

    /**
     * create new album for a given username and name of album
     * @param username
     * @param name
     * @return true if new album created successfully, false otherwise
     */
    public boolean createNewAlbum(String username,String name){
        //TODO: create new album
        return false;
    }

    /**
     * This method update name of a given name album (old name)
     * @param oldName
     * @param newName
     * @param username <- I will use username to specify which album to update.[NOTE: Album names can be duplicated for DIFFERENT users]
     * @return true if update success, false otherwise.
     */
    public boolean updateAlbumName(String username,String oldName,String newName){
        //TODO:update album name
        return false;
    }

    /**
     * This method deletes album given its name and username. Note. DEFAULT ALBUMS CANNOT BE DELETED!!
     * @param username
     * @param name
     * @return true if delete success, false otherwise
     */
    public boolean deleteAlbum(String username,String name){
        return false;
    }


    public List<Image> getImages(String album){
        int albumId = albumRepository.getAlbumByName(album).getId();
        List<Image> imagesOfAlbum =  imageRepository.findImagesByAlbumId(albumId);
        int count =imagesOfAlbum.size();
        System.out.println("Count: "+count);
        return imagesOfAlbum;
    }

}

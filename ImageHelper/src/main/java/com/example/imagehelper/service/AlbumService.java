package com.example.imagehelper.service;


/**
 * This class handles album operations (CRUD)
 */
public class AlbumService {


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


}

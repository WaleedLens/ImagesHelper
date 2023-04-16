package com.example.imagehelper.controller;


import com.example.imagehelper.core.utils.PatternUtils;
import com.example.imagehelper.model.Image;
import com.example.imagehelper.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/album")
public class AlbumController {
    private final AlbumService albumService;


    public AlbumController(AlbumService albumService){
        this.albumService = albumService;
    }


    /**
     * Get all images in given album name.
     * (Note: User should be authenticated , so username can be extracted)
     * @param albumName
     * @return
     */
    @GetMapping("/getImages")
    public List<Image> getImages(@RequestParam String albumName, @RequestHeader("Authorization") String token){
        String username = PatternUtils.extractUsername(token);
        String album = String.join("#",albumName,username);
        System.out.println("Album name: "+album);
        return albumService.getImages(album);
    }


    /**
     * This method delete given album name
     * Note. All images on that Album would be deleted (i.e., moved to trash for X amount of time )
     * X can be modified from application properties :!!)
     * @param albumName
     * @param token
     * @return
     */
    @PostMapping("/deleteAlbum")
    public ResponseEntity deleteAlbum(@RequestParam String albumName,@RequestHeader("Authorization") String token){
        albumService.deleteAlbum(PatternUtils.extractUsername(token),albumName);
        return ResponseEntity.ok().build();
    }

    /**
     * Create new album
     * @param albumName
     * @param token
     * @return
     */

    @PostMapping("/newAlbum")
    public ResponseEntity createAlbum(@RequestParam String albumName,@RequestHeader("Authorization") String token){
        albumService.createNewAlbum(PatternUtils.extractUsername(token),albumName);
        return ResponseEntity.ok().build();
    }

    /**
     * Update existing album name by providing old and new names.
     * @param newName
     * @param oldName
     * @param token
     * @return
     */
    @PutMapping("/newAlbum")
    public ResponseEntity updateAlbumName(@RequestParam String newName,@RequestParam String oldName,@RequestHeader("Authorization") String token){
        albumService.updateAlbumName(PatternUtils.extractUsername(token),oldName,newName);
        return ResponseEntity.ok().build();
    }


}

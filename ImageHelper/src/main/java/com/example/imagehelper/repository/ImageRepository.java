package com.example.imagehelper.repository;

import com.example.imagehelper.model.Image;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image,Integer> {

    List<Image> findImagesByAlbumId(int id);

    Image findImageByImagePointer(String pointer);
    List<Image> findImagesByCreatedAt(LocalDateTime date);
    
}

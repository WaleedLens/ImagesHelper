package com.example.imagehelper.repository;

import com.example.imagehelper.model.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image,Integer> {

    List<Image> findImagesByAlbumId(int id);
}

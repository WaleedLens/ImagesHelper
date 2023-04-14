package com.example.imagehelper.repository;

import com.example.imagehelper.model.Album;
import com.example.imagehelper.model.Image;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album,Integer> {
}

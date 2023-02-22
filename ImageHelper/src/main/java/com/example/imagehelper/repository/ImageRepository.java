package com.example.imagehelper.repository;

import com.example.imagehelper.model.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image,Integer> {
}

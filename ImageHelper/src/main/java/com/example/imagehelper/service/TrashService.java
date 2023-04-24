package com.example.imagehelper.service;


import com.example.imagehelper.model.Image;
import com.example.imagehelper.model.Trash;
import com.example.imagehelper.repository.ImageRepository;
import com.example.imagehelper.repository.TrashRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class TrashService {

    private final TrashRepository trashRepository;
    private final ImageRepository imageRepository;
    Logger log = LoggerFactory.getLogger(TrashService.class);

    public TrashService(TrashRepository trashRepository, ImageRepository imageRepository) {
        this.trashRepository = trashRepository;
        this.imageRepository = imageRepository;
    }


    /**
     * Delete image from  user's album and save deleted image in deleted dir for given duration [default is 30 days , but can be modified from application.properties.]
     *
     * @param id
     */
    public void deleteImage(int id) {
        Image image = imageRepository.findById(id).get();
        Trash trash = new Trash(image.getImagePointer());
        trashRepository.save(trash);
        imageRepository.deleteById(id);
        log.info(image.getImagePointer() + " has been deleted.");
    }


    /**
     * Return days in which the given id of deleted thumbnail had been deleted.
     * @param id
     * @return
     */
    public int getDeletionDuration(int id) {
        Trash trash = trashRepository.findById(id).get();
        LocalDateTime presentDate = LocalDateTime.now();
        LocalDateTime deletedDate = trash.getDeletedAt();
        long durationInDays = DAYS.between(presentDate, deletedDate);
        log.info("ID: " + id + " was deleted " + durationInDays + " ago.");
        return (int) durationInDays;
    }


    public void removePermanently(int id){
        Trash trash = trashRepository.findById(id).get();
        trashRepository.delete(trash);
        log.info("Image of id: " + id + " and pointer of " + trash.getPointer() + " has been removed permanently.");
    }

}

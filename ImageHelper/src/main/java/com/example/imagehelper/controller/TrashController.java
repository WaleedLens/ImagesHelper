package com.example.imagehelper.controller;


import com.example.imagehelper.service.TrashService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trash")
public class TrashController {

    private final TrashService trashService;

    public TrashController(TrashService trashService){
        this.trashService = trashService;
    }


    /**
     * Returns days of a deleted image with a given id.
     * It Calculates the date on which deleted image had been deleted and the current date and counts the days between two dates.
     * @param id
     * @return
     */
    @GetMapping("/{id}/days")
    public int getDaysOfDeletedImage(@PathVariable int id){
        return trashService.getDeletionDuration(id);
    }


    /**
     * Delete image of a given id.
     * Note that this deletion is not permanent.
     * @param id
     * @return
     */
    @PostMapping("/{id}/nonPermanentDelete")
    public ResponseEntity NonPermanentImageDelete(@PathVariable int id){
        trashService.deleteImage(id);
        return ResponseEntity.ok().build();//TODO: modify
    }

    /**
     * Delete image of a given id.
     * Note that this deletion is permanent.
     * @param id
     * @return
     */
    @PostMapping("/{id}/permanentDelete")
    public ResponseEntity permanentImageDelete(@PathVariable int id){
        trashService.removePermanently(id);
        return ResponseEntity.ok().build(); //TODO: modify
    }

}

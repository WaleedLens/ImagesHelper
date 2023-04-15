package com.example.imagehelper.model;


import com.example.imagehelper.core.utils.ImageTypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("image")
public class Image {
    @Id
    private Integer id;
    @Column("imagePointer")
    private String imagePointer;
    @Column("album_id")
    private Integer albumId;

    @Column("imageType")
    private ImageTypes imageType;

    @Column("createdAt")
    private LocalDateTime createdAt;

    public Image(String imagePointer, Integer albumId, ImageTypes imageType) {
        this.imagePointer = imagePointer;
        this.createdAt = LocalDateTime.now();
        this.imageType = imageType;
        this.albumId = albumId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagePointer() {
        return imagePointer;
    }

    public void setImagePointer(String imagePointer) {
        this.imagePointer = imagePointer;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imagePointer='" + imagePointer + '\'' +
                ", albumId=" + albumId +
                ", imageType=" + imageType +
                '}';
    }
}

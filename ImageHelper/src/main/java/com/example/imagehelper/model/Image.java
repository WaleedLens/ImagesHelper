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
    @Column("thumbnailPointer")
    private String thumbnailPointer;
    @Column("album_id")
    private Integer albumId;

    @Column("imageType")
    private ImageTypes imageType;


    public Image(String thumbnailPointer, Integer albumId, ImageTypes imageType) {
        this.thumbnailPointer = thumbnailPointer;
      //  this.createdAt = LocalDateTime.now();
        this.imageType = imageType;
        this.albumId = albumId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThumbnailPointer() {
        return thumbnailPointer;
    }

    public void setThumbnailPointer(String thumbnailPointer) {
        this.thumbnailPointer = thumbnailPointer;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", thumbnailPointer='" + thumbnailPointer + '\'' +
                ", albumId=" + albumId +
                ", imageType=" + imageType +
                '}';
    }
}

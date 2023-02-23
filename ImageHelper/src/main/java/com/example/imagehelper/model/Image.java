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
    @Column("userId")
    private Integer userId;

    @Column("imageType")
    private ImageTypes imageType;


    public Image(String thumbnailPointer, Integer userId, ImageTypes imageType) {
        this.thumbnailPointer = thumbnailPointer;
        this.userId = userId;
      //  this.createdAt = LocalDateTime.now();
        this.imageType = imageType;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", thumbnailPointer='" + thumbnailPointer + '\'' +
                ", userId=" + userId +
                ", imageType=" + imageType +
                '}';
    }
}

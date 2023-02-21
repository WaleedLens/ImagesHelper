package com.example.imagehelper.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class User {

    private long Id;
    private String firstname;
    private String lastname;
    private String avatarPointer;
    private List<Thumbnail> thumbnails;


}

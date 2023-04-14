package com.example.imagehelper.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("user")
public class User {

    @Id
    private Integer id;
    private String firstname;
    private String username;
    private String password;
    private String lastname;
    @Column("avatarPointer")
    private String avatarPointer;
    @Column("createdAt")
    private LocalDateTime createdAt;


    public User(String firstname, String lastname, String password, String username, String avatarPointer) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.avatarPointer = (avatarPointer == null ? "default.png" : avatarPointer);
        this.createdAt = LocalDateTime.now();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAvatarPointer() {
        return avatarPointer;
    }

    public void setAvatarPointer(String avatarPointer) {
        this.avatarPointer = avatarPointer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstname='" + firstname + '\'' + ", username='" + username + '\'' + ", lastname='" + lastname + '\'' + ", avatarPointer='" + avatarPointer + '\'' + ", createdAt=" + createdAt + '}';
    }
}



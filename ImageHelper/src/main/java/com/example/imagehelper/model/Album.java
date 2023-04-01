package com.example.imagehelper.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("album")
public class Album {
    @Id
    private Integer id;

    @MappedCollection(idColumn = "user_id")
    private Set<User> users;
    @Column("name")
    private String name;

    public Album(Set<User> users, String name) {
        this.users = users;
        this.name = name;

    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                "Users count=" + users.size() +
                ", name='" + name + '\'' +
                '}';
    }
}

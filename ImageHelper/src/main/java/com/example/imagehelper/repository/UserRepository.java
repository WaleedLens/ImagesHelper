package com.example.imagehelper.repository;

import com.example.imagehelper.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<User, Integer> {

    int countUserById(Integer id);

}

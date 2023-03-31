package com.example.imagehelper.repository;

import com.example.imagehelper.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User getUserByUsername(String username);

}

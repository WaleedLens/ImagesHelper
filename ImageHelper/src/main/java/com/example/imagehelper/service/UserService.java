package com.example.imagehelper.service;

import com.example.imagehelper.auth.UserDetailsImpl;
import com.example.imagehelper.model.User;
import com.example.imagehelper.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void registerUser(User user) {
        userRepository.save(user);
        LOG.debug("New User has been created. {}", user.getUsername());

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.getUserByUsername(username);
        System.out.println("Test 2");
        return new UserDetailsImpl(user);
    }
}

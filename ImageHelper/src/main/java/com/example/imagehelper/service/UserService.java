package com.example.imagehelper.service;

import com.example.imagehelper.auth.UserDetailsImpl;
import com.example.imagehelper.model.Album;
import com.example.imagehelper.model.User;
import com.example.imagehelper.repository.AlbumRepository;
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
    private final AlbumRepository albumRepository;
    public UserService(UserRepository userRepository,AlbumRepository albumRepository) {
        this.userRepository = userRepository;
        this.albumRepository = albumRepository;
    }

    /**
     * Register new user
     * @param user
     */
    public void registerUser(User user) {
        userRepository.save(user);
        Album album = new Album("default",userRepository.getUserByUsername(user.getUsername()).getId());
        albumRepository.save(album);

        LOG.debug("New User has been created.", user.getUsername());
        LOG.debug("New Album bound to a user has been created.", (album.getName()));


    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.getUserByUsername(username);

        return new UserDetailsImpl(user);
    }
}

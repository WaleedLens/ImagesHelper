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

    public boolean updatePassword(String oldPassword,String username,String newPassword){
        return false;
    }

    private void sendConfirmationNewPasswordEmail(String email){

    }

    /**
     * Register new user in database.
     * Note: every user registered new album shall be created for that specific user with name default#USERNAME. Initially All thumbnails/avatars of a user would be saved in their default album.
     * Note: A user can have more than one album , but It most have at least one album, so default#USERNAME album represents first album for a user
     * @param user
     */
    public void registerUser(User user) {
        userRepository.save(user);
        Album album = new Album("default#"+user.getUsername(),userRepository.getUserByUsername(user.getUsername()).getId());
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

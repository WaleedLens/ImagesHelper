package com.example.imagehelper;

import com.example.imagehelper.model.User;
import com.example.imagehelper.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ImageHelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageHelperApplication.class, args);


    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            userRepository.count();
        };
    }

}

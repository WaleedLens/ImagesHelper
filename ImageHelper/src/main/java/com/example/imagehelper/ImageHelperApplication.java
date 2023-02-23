package com.example.imagehelper;

import com.example.imagehelper.auth.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class ImageHelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageHelperApplication.class, args);


    }


}

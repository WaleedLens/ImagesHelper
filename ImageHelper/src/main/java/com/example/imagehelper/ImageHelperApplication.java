package com.example.imagehelper;

import com.example.imagehelper.auth.RsaKeyProperties;
import com.example.imagehelper.core.utils.KeysGeneratorUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class ImageHelperApplication {

    public static void main(String[] args) {
        KeysGeneratorUtils.generateRSAKeys();
        SpringApplication.run(ImageHelperApplication.class, args);


    }


}

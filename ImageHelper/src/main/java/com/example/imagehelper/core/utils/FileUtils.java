package com.example.imagehelper.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

@Slf4j
public class FileUtils {

    /**
     * Get Resources Directory path. From that path we can get avatars and thumbnails directory.
     * We could make use of enum in this case.
     *
     * @return
     * @throws IOException
     */
    public static String getResourcesPath() throws IOException {
        Resource resource = new ClassPathResource("");
        log.info("Resources Path: " + resource.getURL().getPath());
        return resource.getURL().getPath();
    }

    /**
     * Given image-name generate a unique pointer.
     * Basically this method will generate a file name and that file name would be saved in avatars/thumbnails directory
     * This unique pointer generated from this method would link to a user in database
     * (This would be saved in database)
     *
     * @param imagename
     * @return
     */
    public static String uniqueTokenPointer(String imagename) {
        String randomString = "";
        long randomNumber = -1;
        Random r = new Random();

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 10; i++) {
            randomString = randomString + (alphabet.charAt(r.nextInt(alphabet.length())));
            randomNumber = randomNumber + 395*r.nextLong();
        }
        String uniquePointer = randomNumber + randomString;
        log.info("Generated Unique Pointer: " + uniquePointer);
        return uniquePointer;
    }

    /**
     * @param contentImage
     * @param path
     */
    public static void saveImage(byte[] contentImage, String path) {


        try (FileOutputStream out = new FileOutputStream(new File(path))) {

            out.write(contentImage);

        } catch (IOException e) {
            log.error("Unable to save given file: " + path);
            throw new RuntimeException(e);
        }
    }


    public static String getExtension(String filename){

        return "."+filename.split("\\.")[1];
    }

    public static boolean isImage(String description){
        return false;
    }
}

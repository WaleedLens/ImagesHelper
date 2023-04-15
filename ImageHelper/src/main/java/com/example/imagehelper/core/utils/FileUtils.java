package com.example.imagehelper.core.utils;

import com.example.imagehelper.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    public static String getResourcesPath(){
        URI uri = null;
        try {
            uri = ClassLoader.getSystemResource("certs").toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String mainPath = Paths.get(uri).toString();


        return mainPath;
    }

    /**
     * Given image-name generate a unique pointer.
     * Basically this method will generate a file name and that file name would be saved in avatars/thumbnails directory
     * This unique pointer generated from this method would link to a user in database
     * (This would be saved in database)
     *
     * @param imagename
     * @return


    public static String uniqueTokenPointer(String imagename) {
        String randomString = "";
        long randomNumber = -1;
        Random r = new Random();

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 10; i++) {
            randomString = randomString + (alphabet.charAt(r.nextInt(alphabet.length())));
            randomNumber = randomNumber + 395 * r.nextLong();
        }
        String uniquePointer = randomNumber + randomString;
        log.info("Generated Unique Pointer: " + uniquePointer);
        return uniquePointer;
    }
     */
    public static String generateHash(String imagename){
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(imagename.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @param contentImage
     * @param path
     */
    public static void saveFile(byte[] contentImage, String path) {


        try (FileOutputStream out = new FileOutputStream(new File(path))) {

            out.write(contentImage);

        } catch (IOException e) {
            log.error("Unable to save given file: " + path);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns array of strings. each string represent name of file in given DIR name. [Useful for testing]
     *
     * @param name <--Directory Name
     * @return
     */
    public static String[] contentDirectory(String name) {
        System.out.println(getResourcesPath() + name);
        String[] content = new File(getResourcesPath() +"/"+ name).list();
        System.out.println("Number of files " + content.length + " in " + name + " directory.");
        return content;
        //C:\Users\PC\Documents\GitHub\swe363\ImagesHelper\ImageHelper\target\classes\avatars
    }


    public static String getExtension(String filename) {

        return "." + filename.split("\\.")[1];
    }

    public static boolean isImage(String description) {
        return false;
    }
}

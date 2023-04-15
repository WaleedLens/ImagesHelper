package com.example.imagehelper.core.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;

public class KeysGeneratorUtils {

    /**
     * Generates RSA keys. but first it checks if private and public keys are exist or not. if NOT then generate new key otherwise do not anything.
     */
    public static void generateRSAKeys(){

            try {
                if(!isRSAKeysExist()) {
                    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
                    kpg.initialize(2048);

                    KeyPair kp = kpg.generateKeyPair();
                    Key privateKey = kp.getPrivate();
                    Key publicKey = kp.getPublic();

                    FileUtils.saveFile(privateKey.getEncoded(), FileUtils.getResourcesPath() + "private.pem");
                    FileUtils.saveFile(publicKey.getEncoded(), FileUtils.getResourcesPath() + "public.pem");

                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

    /**
     * Check if RSA keys (public.pem and private.pem) exists in a resource/certs
     * @return
     */
    public static boolean isRSAKeysExist() throws IOException {

       return (Files.list(Path.of(FileUtils.getResourcesPath())).count()) == 2;


    }
}

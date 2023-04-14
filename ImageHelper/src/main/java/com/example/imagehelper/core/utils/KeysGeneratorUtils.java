package com.example.imagehelper.core.utils;

import java.io.IOException;
import java.security.*;

public class KeysGeneratorUtils {


    public static void generateRSAKeys(){
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);

            KeyPair kp = kpg.generateKeyPair();
            Key privateKey = kp.getPrivate();
            Key publicKey = kp.getPublic();
            FileUtils.saveImage(privateKey.getEncoded(),FileUtils.getResourcesPath()+ "certs/private.pem");
            FileUtils.saveImage(privateKey.getEncoded(),FileUtils.getResourcesPath()+ "certs/public.pem");


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ;
    }

    public static void generatePrivateKey(){

    }
}

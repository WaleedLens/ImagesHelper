package com.example.imagehelper.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {
    private static Logger log = LoggerFactory.getLogger(PatternUtils.class);


    /**
     * Extract username from JWT payload
     * @param token
     * @return
     */
    public static String extractUsername(String token){

        String payload = token.split("\\.")[1];
        byte[] decoded = Base64.getDecoder().decode(payload);
        String decodedStr = new String(decoded, StandardCharsets.UTF_8);
        String username = decodedStr.split(":")[2].split(",")[0].replaceAll("\"","");
        log.info("Extracted username: " + username);
        return username;
    }

    /**
     * Extracts username from album name (e.g., default#root) > returns root.
     * @param albumName
     * @return
     */
    public static String extractUsernameFromAlbum(String albumName){
        return albumName.split("#")[1];
    }
}

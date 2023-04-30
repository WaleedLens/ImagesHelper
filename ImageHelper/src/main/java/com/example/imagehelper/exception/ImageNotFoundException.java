package com.example.imagehelper.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * If image is not found in db (i.e., id provided does not exist) <-
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ImageNotFoundException extends RuntimeException{

}

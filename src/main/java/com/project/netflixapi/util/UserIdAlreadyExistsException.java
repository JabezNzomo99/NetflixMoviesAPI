package com.project.netflixapi.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UserIdAlreadyExistsException extends RuntimeException {

    public UserIdAlreadyExistsException(String message){
        super(message);
    }
}

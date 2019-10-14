package com.project.netflixapi.util;

import com.project.netflixapi.models.Category;

public class CategoryDoesNotExist extends RuntimeException {

    public CategoryDoesNotExist(String message){
        super(message);
    }
}

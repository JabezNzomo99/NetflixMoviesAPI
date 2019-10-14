package com.project.netflixapi.services;

import com.project.netflixapi.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
}

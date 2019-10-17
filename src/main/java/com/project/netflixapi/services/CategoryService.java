package com.project.netflixapi.services;

import com.project.netflixapi.models.Category;
import com.project.netflixapi.models.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public List<Category> getMoviesPerCategory(String category);
    public Category addCategory(Category category);
}

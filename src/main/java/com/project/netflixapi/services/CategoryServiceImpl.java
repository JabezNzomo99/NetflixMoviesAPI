package com.project.netflixapi.services;

import com.project.netflixapi.models.Category;
import com.project.netflixapi.models.Movie;
import com.project.netflixapi.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getMoviesPerCategory(String category) {
        return categoryRepository.getMoviesPerCategory(category);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}

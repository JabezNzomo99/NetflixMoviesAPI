package com.project.netflixapi.controllers;

import com.project.netflixapi.models.Category;
import com.project.netflixapi.models.Movie;
import com.project.netflixapi.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {

    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    //Retrieve a list of categories
    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }


    //Function to add a category
    @PostMapping
    public Category addCategory(Category category){
        return categoryService.addCategory(category);
    }
}

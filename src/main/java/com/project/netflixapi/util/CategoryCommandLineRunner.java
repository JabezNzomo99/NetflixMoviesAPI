package com.project.netflixapi.util;

import com.project.netflixapi.models.Category;
import com.project.netflixapi.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CategoryCommandLineRunner implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    public CategoryCommandLineRunner(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        ArrayList<Category> categories = new ArrayList<Category>();
        Category actionCategory = new Category("Action");
        Category romanceCategory = new Category("Romance");
        Category thrillerCategory = new Category("Thriller");
        Category horrorCategory = new Category("Horror");
        Category comedyCategory = new Category("Comedy");
        categories.add(actionCategory);
        categories.add(romanceCategory);
        categories.add(thrillerCategory);
        categories.add(horrorCategory);
        categories.add(comedyCategory);
        categoryRepository.saveAll(categories);
    }
}

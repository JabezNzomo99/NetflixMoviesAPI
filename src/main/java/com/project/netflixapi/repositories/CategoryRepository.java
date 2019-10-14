package com.project.netflixapi.repositories;

import com.project.netflixapi.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    //public List<Category> findMoviesByCategoryName(String categoryName);

}

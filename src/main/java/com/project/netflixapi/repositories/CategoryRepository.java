package com.project.netflixapi.repositories;

import com.project.netflixapi.models.Category;
import com.project.netflixapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    //public List<Category> findMoviesByCategoryName(String categoryName);

    @Query(value = "FROM categories  WHERE categoryName= ?1")
    public List<Category> getMoviesPerCategory(String categoryName);

}

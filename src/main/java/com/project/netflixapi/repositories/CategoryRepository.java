package com.project.netflixapi.repositories;

import com.project.netflixapi.models.Category;
import com.project.netflixapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    //public List<Category> findMoviesByCategoryName(String categoryName);

    @Query(value = "FROM categories  WHERE categoryName= ?1")
    public List<Category> getMoviesPerCategory(String categoryName);

    @Query("SELECT c.movies from categories c where c.categoryName = :categoryName")
    public List<Movie> findMoviesByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT c.movies from categories c where c.categoryName = :categoryId")
    public List<Movie> findMoviesByCategoryId(@Param("categoryId") Long categoryId);

}

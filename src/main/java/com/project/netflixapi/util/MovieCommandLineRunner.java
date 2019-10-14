package com.project.netflixapi.util;

import com.project.netflixapi.models.Category;
import com.project.netflixapi.models.Movie;
import com.project.netflixapi.models.MovieType;
import com.project.netflixapi.repositories.CategoryRepository;
import com.project.netflixapi.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MovieCommandLineRunner implements CommandLineRunner {

    private MovieRepository movieRepository;
    private CategoryRepository categoryRepository;

    public MovieCommandLineRunner(MovieRepository movieRepository, CategoryRepository categoryRepository){
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public void run(String... args) throws Exception {

        Movie movie1 = new Movie();
        movie1.setMovieName("Jamie Fox");
        movie1.setMovieType(MovieType.ORIGINAL);
        movie1.setYearOfRelease("2013");
        Category categorySciFi = categoryRepository.save(new Category("Sci-Fi"));
        Category categoryBollyWood= categoryRepository.save(new Category("BollyWood"));
        Set<Category> categories = new HashSet<>();
        categories.add(categorySciFi);
        categories.add(categoryBollyWood);

        movie1.setCategories(categories);
        movieRepository.save(movie1);


        Movie movie2 = new Movie("50 shades","2010",MovieType.ORIGINAL, categories);
        movieRepository.save(movie2);
    }
}

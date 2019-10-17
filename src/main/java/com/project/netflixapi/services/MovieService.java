package com.project.netflixapi.services;

import com.project.netflixapi.models.Movie;
import com.project.netflixapi.models.MovieType;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    public Movie addMovie(Movie movie);
    public Movie updateMovie(Movie movie);
    public void deleteMovie(Long movieId);
    public List<Movie> getAllMovies();
    public List<Movie> getAllMoviesByCategory();
    public List<Movie> getAllMoviesByMovieType();
    public Movie searchMovieByName(String movieName);
    public Optional<Movie> findMovieById(Long id);
    public List<Movie> findMoviesByUser(Long userId);
    public List<Movie> findMoviesByMovieType(MovieType movieType);
    public List<Movie> findMoviesByMovieTypeAndCategory(MovieType movieType, Long categoryId);

}

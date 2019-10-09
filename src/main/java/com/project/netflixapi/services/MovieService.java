package com.project.netflixapi.services;

import com.project.netflixapi.models.Movie;

import java.util.List;

public interface MovieService {
    public Movie addMovie(Movie movie);
    public Movie updateMovie(Movie movie);
    public Movie deleteMovie(Long movieId);
    public List<Movie> getAllMovies();
    public List<Movie> getAllMoviesByCategory();
    public List<Movie> getAllMoviesByMovieType();

}

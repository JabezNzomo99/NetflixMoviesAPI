package com.project.netflixapi.services;

import com.project.netflixapi.models.Movie;
import com.project.netflixapi.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository){

    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return null;
    }

    @Override
    public Movie deleteMovie(Long movieId) {
        return null;
    }

    @Override
    public List<Movie> getAllMovies() {
        return null;
    }

    @Override
    public List<Movie> getAllMoviesByCategory() {
        return null;
    }

    @Override
    public List<Movie> getAllMoviesByMovieType() {
        return null;
    }
}

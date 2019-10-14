package com.project.netflixapi.services;

import com.project.netflixapi.models.Movie;
import com.project.netflixapi.models.User;
import com.project.netflixapi.repositories.MovieRepository;
import com.project.netflixapi.repositories.UserRepository;
import com.project.netflixapi.util.MovieNotFoundException;
import com.project.netflixapi.util.UserNotFound;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    private UserRepository userRepository;


    public MovieServiceImpl(MovieRepository movieRepository, UserRepository userRepository){
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        Movie retrievedMovie = findMovieById(movie.getMovieId()).orElseThrow(()->new MovieNotFoundException("Movie Not Found"));
        retrievedMovie.setMovieName(movie.getMovieName());
        retrievedMovie.setYearOfRelease(movie.getYearOfRelease());
        retrievedMovie.setUser(movie.getUser());
        retrievedMovie.setCategories(movie.getCategories());
        return movieRepository.save(retrievedMovie);
    }

    @Override
    public void deleteMovie(Long movieId) {
         movieRepository.deleteById(movieId);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getAllMoviesByCategory() {
        return null;
    }

    @Override
    public List<Movie> getAllMoviesByMovieType() {
        return null;
    }

    @Override
    public Movie searchMovieByName(String movieName) throws MovieNotFoundException {
        Movie searchedMovie = movieRepository.findMovieByMovieName(movieName);
        if(searchedMovie == null){
            throw new MovieNotFoundException();
        }else{
            return searchedMovie;
        }
    }

    @Override
    public Optional<Movie> findMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> findMoviesByUser(Long userId) {
        User retrievedUser = userRepository.findById(userId).orElseThrow(()-> new UserNotFound("User with ID: "+userId+" does not exist"));
        return movieRepository.findMoviesByUser(retrievedUser);
    }
}

package com.project.netflixapi.controllers;

import com.project.netflixapi.models.*;
import com.project.netflixapi.repositories.CategoryRepository;
import com.project.netflixapi.repositories.UserRepository;
import com.project.netflixapi.services.MovieService;
import com.project.netflixapi.services.UserService;
import com.project.netflixapi.util.CategoryDoesNotExist;
import com.project.netflixapi.util.MovieNotFoundException;
import com.project.netflixapi.util.UserIdAlreadyExistsException;
import com.project.netflixapi.util.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class MovieController {

    private final MovieService movieService;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    public MovieController(MovieService movieService, UserRepository userRepository, CategoryRepository categoryRepository){
        this.movieService = movieService;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(value = "movies")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }


    @GetMapping(value = "my_movies")
    public List<Movie> getUserMovies(@RequestParam Long userId){
        return movieService.findMoviesByUser(userId);
    }

    @GetMapping(value = "search")
    public Movie searchMovieByName(@RequestParam String movieName){
        try{
            return movieService.searchMovieByName(movieName);
        }catch (MovieNotFoundException exception){
           throw new MovieNotFoundException("Movie with name "+movieName+" not found");
        }
    }

    @PostMapping(value = "suggestMovie")
    public Movie suggestMovie(@RequestBody MovieDto movie){
        User user = userRepository.findById(movie.getUserId()).orElseThrow(()->new UserNotFound("User Not Found"));
        Set<Category> categories = new HashSet<>();
        for(Long id : movie.getCategories()){
           Category category = categoryRepository.findById(id).orElseThrow(()->new CategoryDoesNotExist("Category with Id"+ id +"does not exist"));
           categories.add(category);
        }
        movie.getMovie().setCategories(categories);
        movie.getMovie().setMovieType(MovieType.SUGGESTED);
        movie.getMovie().setUser(user);
        return movieService.addMovie(movie.getMovie());

    }

    @PatchMapping("updateMovie/{id}")
    public Movie updateMovie(
            @PathVariable Long id,
            @RequestBody MovieDto movie){

        User retrievedUser = userRepository.findById(movie.getUserId()).orElseThrow(()->new UserNotFound("User with "+ movie.getUserId() + " not found"));
        Movie retrievedMovie = movieService.findMovieById(id).orElseThrow(()->new MovieNotFoundException("Movie with "+id+" does not exist"));
        if(retrievedUser.getIdentificationNumber() != retrievedMovie.getUser().getIdentificationNumber()){
            throw  new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"You are not the movie owner");
        }else{
            Set<Category> categories = new HashSet<>();
            for(Long categoryId : movie.getCategories()){
                Category category = categoryRepository.findById(categoryId).orElseThrow(()->new CategoryDoesNotExist("Category with Id"+ id +"does not exist"));
                categories.add(category);
            }
            movie.getMovie().setCategories(categories);
            movie.getMovie().setMovieId(retrievedMovie.getMovieId());
            movie.getMovie().setUser(retrievedUser);
            return movieService.updateMovie(movie.getMovie());
        }
    }

    @DeleteMapping("deleteMovie/{id}")
    public void deleteMovie(@PathVariable Long id,
                            @RequestParam Long userId){
        User retrievedUser = userRepository.findById(userId).orElseThrow(()->new UserNotFound("User with ID:  "+ userId + " not found"));
        Movie retrievedMovie = movieService.findMovieById(id).orElseThrow(()->new MovieNotFoundException("Movie with ID: "+id+" does not exist"));
        if(retrievedUser.getIdentificationNumber() != retrievedMovie.getUser().getIdentificationNumber()){
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You are not the movie owner");
        }else {
            movieService.deleteMovie(id);
        }
    }
}

package com.project.netflixapi.controllers;

import com.project.netflixapi.models.*;
import com.project.netflixapi.repositories.CategoryRepository;
import com.project.netflixapi.repositories.UserRepository;
import com.project.netflixapi.services.MovieService;
import com.project.netflixapi.util.CategoryDoesNotExist;
import com.project.netflixapi.util.Create;
import com.project.netflixapi.util.MovieNotFoundException;
import com.project.netflixapi.util.UserNotFound;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@Api(value="Netlix Rest API", description="Operations pertaining to movies")
public class MovieController {

    private final MovieService movieService;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    //Dependency injections of components
    public MovieController(MovieService movieService, UserRepository userRepository, CategoryRepository categoryRepository){
        this.movieService = movieService;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    //Returns a list of all movies
    @GetMapping(value = "movies",produces = "application/json")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    //Returns a list of movies per category and type
    @GetMapping(value = "movies/{categoryId}")
    public List<Movie> getMoviesPerCategoryAndType(@PathVariable Long categoryId,
                                                   @RequestParam MovieType movieType) throws CategoryDoesNotExist{
        categoryRepository.findById(categoryId).orElseThrow(()->new CategoryDoesNotExist(" Category with ID: "+categoryId+" does not exist"));
        return movieService.findMoviesByMovieTypeAndCategory(movieType,categoryId);
    }

    //Returns a list of movies belonging to a particular user
    @GetMapping(value = "my_movies/{userId}")
    public List<Movie> getUserMovies(@PathVariable Long userId){
        return movieService.findMoviesByUser(userId);
    }

    //Returns a movie corresponding to the movieId passed
    @GetMapping(value = "movies/search/{movieId}")
    public Movie getMovieById(@PathVariable Long movieId) throws MovieNotFoundException{
        return movieService.findMovieById(movieId).orElseThrow(()-> new MovieNotFoundException("Movie with id "+ movieId + " does not exist"));
    }

    //Returns a movie that matches the movie name the user passes
    @GetMapping(value = "movies/search")
    public List<Movie> searchMovieByName(@RequestParam String movieName) throws MovieNotFoundException{
        try{
            return movieService.searchMoviesByName(movieName);
        }catch (MovieNotFoundException exception){
           throw new MovieNotFoundException("Movie with name "+movieName+" not found");
        }
    }

    //Returns a list of movies belonging to a particular category by passing the category name
    @GetMapping(value = "movies/category/{categoryName}")
    public List<Movie> getMoviesByCategoryName(@PathVariable String categoryName){
        return categoryRepository.findMoviesByCategoryName(categoryName);
    }


//    //Returns a list of movies belonging to a particular category by passing the category id
//    @GetMapping(value = "movies/category/{categoryId}")
//    public List<Movie> getMoviesByCategoryId(@PathVariable Long categoryId){
//        return categoryRepository.findMoviesByCategoryId(categoryId);
//    }



    //Allows user to suggest a movie
    @PostMapping(value = "movies/{userId}")
    public Movie suggestMovie(@PathVariable Long userId,
                              @Validated(value = Create.class) @RequestBody MovieDto movie) throws CategoryDoesNotExist{
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFound("User with ID:"+ userId+ " not found"));
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

    //Allow user to update a movie
    @PatchMapping("movies/{userId}/{movieId}")
    public Movie updateMovie(
            @PathVariable Long userId,
            @PathVariable Long movieId,
            @RequestBody MovieDto movie) throws MovieNotFoundException,CategoryDoesNotExist, UserNotFound{

        User retrievedUser = userRepository.findById(userId).orElseThrow(()->new UserNotFound("User with "+ userId + " not found"));
        Movie retrievedMovie = movieService.findMovieById(movieId).orElseThrow(()->new MovieNotFoundException("Movie with "+movieId+" does not exist"));
        if (retrievedUser.getIdentificationNumber() != retrievedMovie.getUser().getIdentificationNumber()) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "You are not the movie owner");
        } else {
                Set<Category> categories = new HashSet<>();
                for (Long categoryId : movie.getCategories()) {
                    Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryDoesNotExist("Category with Id" + categoryId + "does not exist"));
                    categories.add(category);
                }
                movie.getMovie().setCategories(categories);
                movie.getMovie().setMovieId(retrievedMovie.getMovieId());
                movie.getMovie().setUser(retrievedUser);
                return movieService.updateMovie(movie.getMovie());
            }
        }

    //Allow user to delete a movie
    @DeleteMapping("movies/{userId}/{movieId}")
    public void deleteMovie(@PathVariable Long movieId,
                            @PathVariable Long userId) throws UserNotFound,MovieNotFoundException{
        User retrievedUser = userRepository.findById(userId).orElseThrow(()->new UserNotFound("User with ID:  "+ userId + " not found"));
        Movie retrievedMovie = movieService.findMovieById(movieId).orElseThrow(()->new MovieNotFoundException("Movie with ID: "+movieId+" does not exist"));
        if(retrievedUser.getIdentificationNumber() != retrievedMovie.getUser().getIdentificationNumber()){
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You are not the movie owner");
        }else {
            movieService.deleteMovie(movieId);
        }
    }

    //Allow users to retrieve movie of particular type
    @GetMapping("movies/type")
    public List<Movie> findMoviesByMovieType(@RequestParam MovieType movieType){
        return movieService.findMoviesByMovieType(movieType);
    }


}

package com.project.netflixapi.services;

import com.project.netflixapi.models.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "client", url = "")

public interface FeignRestClient {

    //endpoint to add/register user
    @RequestMapping(method = RequestMethod.POST, value = "register")
    User addUser(@RequestBody User user);

    //endpoint to view all users
    @RequestMapping(method = RequestMethod.GET, value = "users")
    List<User> getAllUsers();

    //endpoint to view all categories
    @RequestMapping(method = RequestMethod.GET, value = "categories")
    List<Category> getAllCategories();

    //endpoint to view movies as per the category
    @RequestMapping(method = RequestMethod.GET, value = "movies/{category}")
    List<Category> getMoviesPerCategory(@PathVariable String category);

    //endpoint to add category
    @RequestMapping(method =  RequestMethod.POST, value = "addCategory")
    Category addCategory(Category category);

    //endpoint to view all movies
    @RequestMapping(method = RequestMethod.GET, value ="movies")
    List<Movie> getAllMovies();

    //endpoint to view a list of movies per category and type
    @RequestMapping(method = RequestMethod.GET, value ="movies/{categoryId}")
    List<Movie> getMoviesPerCategoryAndType(@PathVariable Long categoryId, @RequestParam MovieType movieType);

    //endpoint to view movies of a particular user
    @RequestMapping(method = RequestMethod.GET, value = "my_movies/{userId}")
    List<Movie> getUserMovies(@PathVariable Long userId);

    //endpoint to search for a movie by Id
    @RequestMapping(method = RequestMethod.GET, value = "movies/search/{movieId}")
    Movie getMovieById(@PathVariable Long movieId);

    //endpoint to search for a movie by Name
    @RequestMapping(method = RequestMethod.GET, value="movies/search")
    Movie searchMovieByName(@RequestParam String movieName);

    //endpoint to suggest a movie
    @RequestMapping(method = RequestMethod.POST, value = "movies/{userId}" )
    Movie suggestMovie(@PathVariable Long userId,@RequestBody MovieDto movie);

    //endpoint to update a movie
    @RequestMapping(method = RequestMethod.PATCH, value = "movies/{userId}/{movieId}" )
    Movie updateMovie(@PathVariable Long userId, @PathVariable Long movieId, @RequestBody MovieDto movie);

    //endpoint to delete a movie
    @RequestMapping(method = RequestMethod.DELETE, value = "movies/{userId}/{movieId}" )
    Movie deleteMovie(@PathVariable Long userId, @PathVariable Long movieId);

    //endpoint to retrieve movies of a particular type
    @RequestMapping( method =  RequestMethod.GET, value = "movies/type")
    List<Movie> findMoviesByMovieType(@RequestParam MovieType movieType);
















}

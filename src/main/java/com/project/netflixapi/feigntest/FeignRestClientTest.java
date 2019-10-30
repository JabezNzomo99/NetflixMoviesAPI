package com.project.netflixapi.feigntest;

import com.project.netflixapi.models.*;
import com.project.netflixapi.util.UserIdAlreadyExistsException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FeignRestClientTest implements CommandLineRunner {

    private final FeignRestClient feignRestClient;
    private User createdUser = new User("Test",1234L);

    public FeignRestClientTest(FeignRestClient feignRestClient) {
        this.feignRestClient = feignRestClient;
    }

    @Override
    public void run(String... args) throws Exception {

        //Create User
        try {
            createdUser = feignRestClient.addUser(new User("Test", 1234L));
            System.out.println(createdUser.toString());
        } catch (UserIdAlreadyExistsException exception) {
            System.out.println(exception.getMessage());
        }

        //Admin Purposes - Display a list of users
        List<User> allUsers = feignRestClient.getAllUsers();
        System.out.println(allUsers.toString());

        //Admin add category
        Category addedCategory = feignRestClient.addCategory(new Category("POP"));
        System.out.println(addedCategory.toString());

        //Retrieve all categories
        List<Category> categoryList = feignRestClient.getCategories();
        System.out.println(categoryList.toString());

        //Retrieve All Movies
        List<Movie> movieList = feignRestClient.getAllMovies();
        System.out.println(movieList.toString());

        //Retrieve movies by category and type
        try {
            List<Movie> movieList1 = feignRestClient.getMoviesPerCategoryAndType(1L, MovieType.ORIGINAL);
            System.out.println(movieList1.toString());
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        //Suggest Movie
        try{
            Movie testMovie = new Movie();
            testMovie.setMovieName("Jamie Jamie");
            ArrayList<Long> categoriesId = new ArrayList<>();
            categoriesId.add(1L);
            testMovie.setYearOfRelease("2015");
            testMovie = feignRestClient.suggestMovie(createdUser.getIdentificationNumber(),
                    new MovieDto( testMovie, categoriesId));
            System.out.println(testMovie.toString());
        }catch(Exception exception) {
            System.out.println(exception.getMessage());
        }

        //Get User Movies
        try{
            List<Movie> userMovies = feignRestClient.getUserMovies(createdUser.getIdentificationNumber());
            System.out.println(userMovies.toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //Get Movie by movie id
        try{
            Movie searchedMovieById = feignRestClient.getMovieById(2L);
            System.out.println(searchedMovieById.toString());
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        //Search movie by name
        try {
            Movie searchedMovieByName = feignRestClient.searchMovieByName("Jamie Jamie");
            System.out.println(searchedMovieByName.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        //get movies by category name
        try {
                List<Movie> moviesByCategoryName = feignRestClient.getMoviesByCategoryName("Action");
                System.out.println(moviesByCategoryName.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            List<Movie> moviesByMovieType = feignRestClient.findMoviesByMovieType(MovieType.ORIGINAL);
            System.out.println(moviesByMovieType.toString());
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}

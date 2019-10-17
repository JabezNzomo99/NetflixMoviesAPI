package com.project.netflixapi.controllers;

import com.project.netflixapi.models.User;
import com.project.netflixapi.services.UserService;
import com.project.netflixapi.util.Create;
import com.project.netflixapi.util.UserIdAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Function to register or add a user
    @PostMapping
    public User addUser(@Validated(value = Create.class) @RequestBody User user) {
        try {
            return userService.addUser(user);
        }catch (UserIdAlreadyExistsException exception){
           throw new UserIdAlreadyExistsException("User Id Already Exists");
        }
    }

    //Retrieve a list of users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}

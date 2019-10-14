package com.project.netflixapi.services;


import com.project.netflixapi.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "client", url = "")

public interface FeignRestClient {

    @RequestMapping(method = RequestMethod.POST, value = "register")
    User addUser(@RequestBody User user);

    @RequestMapping(method = RequestMethod.GET, value = "users")
    List<User> getAllUsers();




}

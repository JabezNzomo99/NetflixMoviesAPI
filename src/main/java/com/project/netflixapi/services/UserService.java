package com.project.netflixapi.services;

import com.project.netflixapi.models.User;

import java.util.List;

public interface UserService {
    public User addUser(User user);

    public void deleteUser(Long userId);

    public User updateUser(User user);

    public List<User> getAllUsers();
}

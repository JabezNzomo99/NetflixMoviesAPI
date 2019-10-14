package com.project.netflixapi.services;

import com.project.netflixapi.models.User;
import com.project.netflixapi.repositories.UserRepository;
import com.project.netflixapi.util.UserIdAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) throws UserIdAlreadyExistsException {
        if(checkIfUserExists(user.getIdentificationNumber())){
           throw new UserIdAlreadyExistsException("User Id Already Exists");
        }else{
            return userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Boolean checkIfUserExists(Long identificationNumber) {
        return userRepository.existsUserByIdentificationNumber(identificationNumber);
    }
}

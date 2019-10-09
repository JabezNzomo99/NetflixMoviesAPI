package com.project.netflixapi.repositories;

import com.project.netflixapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findUserByIdentificationNumber(String identificationNumber);
}

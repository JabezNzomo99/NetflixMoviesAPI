package com.project.netflixapi.models;

import com.project.netflixapi.util.Create;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
public class User {

    @NotNull(groups = Create.class)
    private String userName;

    @Id
    @Column(name = "userId",unique = true)
    @NotNull(groups = Create.class)
    private Long identificationNumber;

    public User(String userName, Long identificationNumber) {
        this.userName = userName;
        this.identificationNumber = identificationNumber;
    }

    private User(){
        
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Long getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(Long identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
}

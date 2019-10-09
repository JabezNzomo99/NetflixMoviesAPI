package com.project.netflixapi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "users")
public class User {

    private String userName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String identificationNumber;

    public User(String userName, String identificationNumber) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
}

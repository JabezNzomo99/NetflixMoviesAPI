package com.project.netflixapi.models;

import com.project.netflixapi.util.Create;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@ApiModel(description = "All details about the user")
public class User {

    @NotNull(groups = Create.class)
    @ApiModelProperty("The user name")
    private String userName;

    @Id
    @Column(name = "userId",unique = true)
    @NotNull(groups = Create.class)
    @ApiModelProperty("The user identification number")
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


    @Override
    public String toString() {
        return "User{" +
                "User Name :"+userName+
                "Identification Number :"+identificationNumber+
                "}";
    }
}

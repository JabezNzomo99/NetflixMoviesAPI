package com.project.netflixapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.netflixapi.util.Create;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "movies")
@ApiModel(description = "Details about a movie")
public class Movie {

    @Id
    @Column(name = "movieId", unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(notes = "The database generated movie ID")
    private Long movieId;

    @NotNull(groups = Create.class)
    @ApiModelProperty(notes = "The movie name")
    private String movieName;

    @NotNull(groups = Create.class)
    @ApiModelProperty(notes = "The movie year of release")
    private String yearOfRelease;

    @ApiModelProperty(notes = "The movie type (SUGGESTED/ORIGINAL)")
    private MovieType movieType;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "category_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")

    )
    @ApiModelProperty(notes = "The categories the movie belongs to")
    private Set<Category> categories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie(){

    }

    public Movie(String movieName, String yearOfRelease, MovieType movieType, Set<Category> categories) {
        this.movieName = movieName;
        this.yearOfRelease = yearOfRelease;
        this.movieType = movieType;
        this.categories = categories;
    }

    public Movie(String movieName, String yearOfRelease, MovieType movieType) {
        this.movieName = movieName;
        this.yearOfRelease = yearOfRelease;
        this.movieType = movieType;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Movie Id : "+movieId+
                "Movie Name :"+movieName+
                "Year of Release:"+yearOfRelease+
                "Categories"+categories+
                "}";
    }
}

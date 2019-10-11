package com.project.netflixapi.models;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieId;

    private String movieName;

    private String yearOfRelease;

    private MovieType movieType;

    @ManyToMany(mappedBy = "movies")
    private Set<Category> categories;

    public Movie(){

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
}

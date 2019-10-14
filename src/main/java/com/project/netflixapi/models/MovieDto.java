package com.project.netflixapi.models;

import java.util.ArrayList;
import java.util.List;

public class MovieDto {

    private Long userId;
    private Movie movie;
    private List<Long> categories;

    public MovieDto(Long userId, Movie movie, List<Long> categories) {
        this.userId = userId;
        this.movie = movie;
        this.categories = categories;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }
}

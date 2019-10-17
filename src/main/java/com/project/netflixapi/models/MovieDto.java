package com.project.netflixapi.models;

import com.project.netflixapi.util.Create;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class MovieDto {


    @Valid
    private Movie movie;

    @NotNull(groups = Create.class)
    private List<Long> categories;

    public MovieDto(Movie movie, List<Long> categories) {
        this.movie = movie;
        this.categories = categories;
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

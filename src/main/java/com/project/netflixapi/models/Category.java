package com.project.netflixapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.netflixapi.util.Create;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "categories")
@ApiModel(description = "All details about a category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(notes = "The database generated category id")
    private Long categoryId;

    @NotNull(groups = Create.class)
    @ApiModelProperty(notes = "The category name")
    private String categoryName;

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "categories", fetch = FetchType.LAZY)
    @JsonIgnore
    @ApiModelProperty(notes = "The movies contained in the category")
    private Set<Movie> movies = new HashSet<>();

    public Category(){}

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                " Category Id:"+categoryId+
                " Category Name:"+categoryName+
                "}";
    }
}

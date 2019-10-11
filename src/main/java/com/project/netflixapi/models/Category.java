package com.project.netflixapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    private String categoryName;


    @ManyToMany
    @JoinTable(
            name = "category_movies",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")

    )
    @JsonIgnore
    private Set<Movie> movies;
    //private CategoryName categoryName;


    public Category(){}

    private Category(String categoryName) {
        this.categoryName = categoryName;
    }
//    private Category(CategoryName categoryName) {
//        this.categoryName = categoryName;
//    }

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
}

package com.project.netflixapi.repositories;

import com.project.netflixapi.models.Movie;
import com.project.netflixapi.models.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    public List<Movie> findMoviesByMovieType(MovieType movieType);
}

package com.bookMyShow.api;

import com.bookMyShow.service.MovieService;

public class MovieController {
    MovieService movieService;

    MovieController(MovieService movieService){
        this.movieService=movieService;
    }

    public String createMovie(String movieName){
        return movieService.createMovie(movieName).getId();
    }
}

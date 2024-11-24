package com.bookMyShow.service;

import com.bookMyShow.model.Movie;

import java.rmi.NotBoundException;
import java.util.HashMap;
import java.util.UUID;

public class MovieService {
    private final HashMap<String,Movie> movies;

    MovieService(){
        this.movies=new HashMap<>();
    }
    public Movie createMovie(String movieName){
        String movieId= UUID.randomUUID().toString();
        Movie newmovie=new Movie(movieId,movieName);
        movies.put(movieName,newmovie);
        return newmovie;
    }

    public Movie getMovie(String movieId){
        if(!movies.containsKey(movieId)){
            throw new NotFoundException();
        }
        return movies.get(movieId);
    }
}

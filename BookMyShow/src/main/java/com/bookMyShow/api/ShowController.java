package com.bookMyShow.api;

import com.bookMyShow.model.Screen;
import com.bookMyShow.model.Movie
import com.bookMyShow.service.MovieService;
import com.bookMyShow.service.TheatreService;

import java.util.Date;

public class ShowController {
    private ShowService showService;
    private MovieService movieService;
    private TheatreService theatreService;

    public String createShow(String screenId, String movieId, Date startTime, Integer duration){
        Screen screen=theatreService.getScreen(screenId);
        Movie movie=movieService.getMovie(movieId);

        return showService.createShow(screen,movie,startTime,duration).getId();
    }


}

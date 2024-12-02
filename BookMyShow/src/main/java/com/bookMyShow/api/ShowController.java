package com.bookMyShow.api;

import com.bookMyShow.model.Screen;
import com.bookMyShow.model.Movie
import com.bookMyShow.model.Seat;
import com.bookMyShow.model.Show;
import com.bookMyShow.service.MovieService;
import com.bookMyShow.service.SeatAvailabilityService;
import com.bookMyShow.service.ShowService;
import com.bookMyShow.service.TheatreService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ShowController {
    private ShowService showService;
    private MovieService movieService;
    private TheatreService theatreService;
    private SeatAvailabilityService seatAvailabilityService;

    public String createShow(String screenId, String movieId, Date startTime, Integer duration){
        Screen screen=theatreService.getScreen(screenId);
        Movie movie=movieService.getMovie(movieId);

        return showService.createShow(movie,screen,startTime,duration).getId();
    }

    public List<String> getAvailableSeats(String showId){
        Show show=showService.getShow(showId);
        List<Seat> availableSeats= seatAvailabilityService.getAvailableSeats(show);
        return availableSeats.stream().map(Seat::getId).collect(Collectors.toList());

    }

}

package com.bookMyShow.service;

import com.bookMyShow.exception.NotFoundException;
import com.bookMyShow.model.Movie;
import com.bookMyShow.model.Screen;
import com.bookMyShow.model.Show;

import java.util.*;

public class ShowService {
    private HashMap<String, Show> shows;

    public ShowService(){
        shows=new HashMap<>();
    }

    public Show getShow(String showId){
        if(!shows.containsKey(showId)){
            throw new NotFoundException();
        }
        return shows.get(showId);
    }

    public Show createShow(Movie movie, Screen screen, Date startTime, Integer duration){
        String showId= UUID.randomUUID().toString();
        Show show=new Show(showId,screen,movie,startTime,duration);
        shows.put(showId,show);
        return show;
    }

    public List<Show> getShowsForScreen(Screen screen){
        List<Show> response=new ArrayList<>();
        for(Show show:shows.values()){
            if(screen.equals(show.getScreen())){
                response.add(show);
            }
        }
        return response;
    }
}

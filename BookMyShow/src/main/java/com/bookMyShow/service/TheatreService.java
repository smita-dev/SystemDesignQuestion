package com.bookMyShow.service;

import com.bookMyShow.exception.NotFoundException;
import com.bookMyShow.model.Screen;
import com.bookMyShow.model.Seat;
import com.bookMyShow.model.Theatre;

import java.util.HashMap;
import java.util.UUID;

public class TheatreService {
    HashMap<String, Theatre>theatres;
    HashMap<String, Screen>screens;
    HashMap<String, Seat> seats;

    TheatreService(){
        theatres=new HashMap<>();
        screens=new HashMap<>();
        seats=new HashMap<>();
    }

    public Theatre getTheatre(String theatreId){
        if(!theatres.containsKey(theatreId)){
            throw new NotFoundException();
        }
        return theatres.get(theatreId);
    }

    public Screen getScreen(String screenId){
        if(screens.containsKey(screenId)){
            throw new NotFoundException();
        }

        return screens.get(screenId);
    }

    public Seat getSeat(String seatId){
        if(seats.containsKey(seatId)){
            throw new NotFoundException();
        }

        return seats.get(seatId);
    }

    public Theatre createTheatre(String theatreName){
        String theatreId= UUID.randomUUID().toString();
        Theatre newTheatre=new Theatre(theatreId,theatreName);
        theatres.put(theatreId,newTheatre);
        return newTheatre;
    }

    public Screen createScreen(String screenName, Theatre theatre){
        String screenId=UUID.randomUUID().toString();
        Screen newScreen=new Screen(screenId,screenName,theatre);
        screens.put(screenId,newScreen);
        return newScreen;
    }

    public Screen createScreenInTheatre(String screenName,Theatre theatre){
        Screen newScreen=createScreen(screenName,theatre);
        theatre.addScreen(newScreen);
        return newScreen;
    }

    public Seat createSeatInScreen(Integer rowNo, Integer seatNo, Screen screen){
        String seatId=UUID.randomUUID().toString();
        Seat seat=new Seat(seatId,rowNo,seatNo);
        screen.addSeat(seat);
        return seat;
    }
}

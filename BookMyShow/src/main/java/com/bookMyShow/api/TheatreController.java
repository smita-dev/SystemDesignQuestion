package com.bookMyShow.api;

import com.bookMyShow.model.Screen;
import com.bookMyShow.model.Theatre;
import com.bookMyShow.service.TheatreService;

public class TheatreController {
    private TheatreService theatreService;

    TheatreController(TheatreService theatreService){
        this.theatreService=theatreService;
    }

    public String createTheatre(String theatreName){
        return theatreService.createTheatre(theatreName).getId();
    }

    public String createScreenInTheatre(String screenName,String theatreId){
        Theatre theatre=theatreService.getTheatre(theatreId);
        return theatreService.createScreenInTheatre(theatre,screenName).getId();
    }

    public String createSeatInScreen(Integer rowNo, Integer seatNo, String screenId ){
        Screen screen=theatreService.getScreen(screenId);
        return theatreService.createSeatInScreen(rowNo, seatNo,screen).getId();
    }
}

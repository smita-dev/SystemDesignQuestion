package com.bookMyShow.api;

import com.bookMyShow.model.Seat;
import com.bookMyShow.model.Show;
import com.bookMyShow.service.MovieService;
import com.bookMyShow.service.ShowService;
import com.bookMyShow.service.TheatreService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BookingController {
    BookingService bookingService;
    TheatreService theatreService;
    ShowService showService;

    public String createBooking(String userId, String showId, List<String> seatIds){
        Show show=showService.getShow(showId);
        List<Seat> seats=seatIds.stream().map(theatreService::getSeat).toList();
        return bookingService.createBooking(userId,show,seats);
    }

}

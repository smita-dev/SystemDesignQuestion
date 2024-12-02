package com.bookMyShow.service;

import com.bookMyShow.model.Seat;
import com.bookMyShow.model.Show;
import com.bookMyShow.providers.SeatLockProvider;

import java.util.ArrayList;
import java.util.List;

public class SeatAvailabilityService {
    private BookingService bookingService;
    private SeatLockProvider seatLockProvider;

    public SeatAvailabilityService(BookingService bookingService,
                                   SeatLockProvider seatLockProvider){
        this.bookingService=bookingService;
        this.seatLockProvider=seatLockProvider;
    }

    public List<Seat> getAvailableSeats(Show show){
        List<Seat> allSeats=show.getScreen().getSeats();
        List<Seat> unavailableSeats=getUnAvailableSeats(show);

        List<Seat> availableSeats=new ArrayList<>(allSeats);
        availableSeats.removeAll(unavailableSeats);
        return availableSeats;
    }

    private List<Seat> getUnAvailableSeats(Show show){
        List<Seat> unavailableSeats=bookingService.getBookedSeats(show);
        unavailableSeats.addAll(seatLockProvider.getLockedSeats(show));
        return unavailableSeats;
    }
}

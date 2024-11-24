package com.bookMyShow.service;

import com.bookMyShow.exception.NotFoundException;
import com.bookMyShow.model.Booking;
import com.bookMyShow.model.BookingStatus;
import com.bookMyShow.model.Seat;
import com.bookMyShow.model.Show;

import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    HashMap<String, Booking> showBookings;

    public BookingService(){
        showBookings=new HashMap<>();
    }

    public Booking createBooking(String userId, Show show, List<Seat> seats){
        String bookingId= UUID.randomUUID().toString();
        Booking booking=new Booking(bookingId,show,userId,seats);
        showBookings.put(bookingId,booking);
        return booking;
    }

    public Booking getBooking(String bookingId){
        if(!showBookings.containsKey(bookingId)){
            throw new NotFoundException();
        }
        return showBookings.get(bookingId);
    }

    public List<Booking> getAllBooking(Show show){
        List<Booking> response=new ArrayList<>();
        for(Booking booking:showBookings.values()){
            if(booking.getShow().equals(show)){
                response.add(booking);
            }
        }
        return response;
    }

    public List<Seat> getBookedSeats(Show show){
        return getAllBooking(show).stream().
                filter(Booking::isbookingConfirmed).
                map(Booking::getBookedSeats).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public void confirmBooking(Booking booking, String user){
        booking.setbookingStatus(BookingStatus.Confirmed);
    }
}

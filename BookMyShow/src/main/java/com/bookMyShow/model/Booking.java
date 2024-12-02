package com.bookMyShow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Booking {
    private String id;
    private Show show;
    private String user;
    private List<Seat> bookedSeats;
    private BookingStatus bookingStatus;

    Booking(String id, Show show, String user, List<Seat> bookedSeats){
        this.id=id;
        this.show=show;
        this.user=user;
        this.bookedSeats=bookedSeats;
        this.bookingStatus=BookingStatus.Created;
    }

    public boolean isbookingConfirmed(Booking booking){
        return booking.bookingStatus==BookingStatus.Confirmed;
    }

    public void confirmBooking(Booking booking){
        booking.bookingStatus=BookingStatus.Confirmed;
    }

    public void expireBooking(Booking booking){
        booking.bookingStatus=BookingStatus.Expired;
    }
}

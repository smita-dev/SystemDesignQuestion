package com.bookMyShow.api;

import com.bookMyShow.service.BookingService;

public class PaymentController {
    private PaymentService paymentService;
    private BookingService bookingService;

    public PaymentController(PaymentService paymentService,BookingService bookingService){
        this.paymentService=paymentService;
        this.bookingService=bookingService;
    }

    public void paymentFailed(String bookingId, String user){
        paymentService.processPaymentFailed(bookingService.getBooking(bookingId),user);
    }

    public void paymentSuccess(String bookingId, String user){
        bookingService.confirmBooking(bookingService.getBooking(bookingId),user);
    }
}

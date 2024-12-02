package com.bookMyShow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class SeatLock {
    private Seat seat;
    private Show show;
    private Integer timeoutInSeconds;
    private Date lockTime;
    private String lockedBy;

    public boolean isLockExpired(){
        Instant lockInstant=lockTime.toInstant().plusSeconds(timeoutInSeconds);
        Instant currentInstant=new Date().toInstant();

        return lockInstant.isBefore(currentInstant);
    }
}

package com.bookMyShow.providers;

import com.bookMyShow.model.Seat;
import com.bookMyShow.model.SeatLock;
import com.bookMyShow.model.Show;

import java.util.*;

public class InMemorySeatLockProvider implements SeatLockProvider{

    private Integer lockTimeout;
    private Map<Show, Map<Seat, SeatLock>> locks;

    public InMemorySeatLockProvider(Integer lockTimeout){
        this.locks=new HashMap<>();
        this.lockTimeout=lockTimeout;
    }
    @Override
    public void lockSeats(Show show, List<Seat> seats, String user) {
        for(Seat seat: seats){
            if(isSeatLocked(show,seat)){
                throw new SeatTemporaryUnavailableException();
            }
        }

        for(Seat seat: seats){
            lockSeat(show,seat,user,lockTimeout);
        }
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats, String user) {
        for(Seat seat:seats){
            if(validateLock(show,seat,user)){
                unlockSeat(show,seat);
            }
        }
    }

    @Override
    public boolean validateLock(Show show, Seat seat, String user) {
        return isSeatLocked(show,seat) && locks.get(show).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        if(!locks.containsKey(show)){
            return ImmutableList.of();
        }

        List<Seat> lockedSeats=new ArrayList<>();

        for(Seat seat:locks.get(show).keySet()){
            if(isSeatLocked(show,seat)){
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }

    private void unlockSeat(Show show,Seat seat){
        if(!locks.containsKey(show)){
            return;
        }

        locks.get(show).remove(seat);
    }

    private void lockSeat(Show show, Seat seat,String user,Integer timeoutInSeconds){
        if(!locks.containsKey(show)){
            locks.put(show,new HashMap<>());
        }
        SeatLock seatLock=new SeatLock(seat,show,timeoutInSeconds,new Date(),user);
        locks.get(show).put(seat,seatLock);
    }

    private boolean isSeatLocked(Show show, Seat seat){
        return locks.containsKey(show) && locks.get(show).containsKey(seat) && locks.get(show).get(seat).isLockExpired();
    }
}

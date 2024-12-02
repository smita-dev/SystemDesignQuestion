package com.bookMyShow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@AllArgsConstructor
@Setter
public class Show {
    private String id;
    private Movie movie;
    private Screen screen;
    private Date startTime;
    private Integer durationInSeconds;
}

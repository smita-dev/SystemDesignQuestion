package com.bookMyShow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Theatre {
    private String id;
    private String name;
    private List<Screen> screens;

    public Theatre(@NonNull String id, @NonNull String name){
        this.id=id;
        this.name=name;
        screens=new ArrayList<>();
    }

    public void addScreen(Screen screen){
        screens.add(screen);
    }
}

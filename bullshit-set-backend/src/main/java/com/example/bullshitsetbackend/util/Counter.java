package com.example.bullshitsetbackend.util;

import lombok.Synchronized;

public class Counter {

    private int currentActiveGames = 0;
    private int totalGames = 0;

    @Synchronized
    private int getTotalGames() {
        return this.totalGames;
    }

    @Synchronized
    private void incrementGameCounter() {
        this.currentActiveGames+=1;
        this.totalGames+=1;
    }

    @Synchronized
    private void decrementGameCounter() {
        this.currentActiveGames-=1;
    }


}

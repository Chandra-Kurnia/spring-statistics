package com.application.clasementAPI.pojos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Statistics {
    private int homeTeamGoal;
    private int awayTeamGoal;

    public Statistics(int homeTeamGoal, int awayTeamGoal) {
        this.homeTeamGoal = homeTeamGoal;
        this.awayTeamGoal = awayTeamGoal;
    }
}
package com.application.clasementAPI.pojos;

import com.application.clasementAPI.entities.Matchs;
import com.application.clasementAPI.entities.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class StatisticMatch {

    private Long matchId;
    private Long homeTeam;
    private Long awayTeam;
    private LocalDate schedule;
    private Statistics statistics;
    private Status status;

    public StatisticMatch(){}

    public StatisticMatch(Long matchId, Long homeTeam, Long awayTeam, LocalDate schedule, Statistics statistics, Status status) {
        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.schedule = schedule;
        this.statistics = statistics;
        this.status = status;
    }

    public StatisticMatch setStatisticMatch(Matchs match) {
        Statistics statistics = new Statistics(
                match.getHomeTeam().getHomeGoal(),
                match.getAwayTeam().getAwayGoal()
        );

        this.matchId = (Long) match.getId();
        this.homeTeam = (Long) match.getHomeTeam().getId();
        this.awayTeam = (Long) match.getAwayTeam().getId();
        this.schedule = (LocalDate) match.getSchedule();
        this.status = (Status) match.getStatus();
        this.statistics = statistics;

        return this;
    }
}

package com.application.clasementAPI.pojos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.List;

@Setter
@Getter
public class ListClasement {
    private BigInteger id;
    private String teamName;
    private String city;
    private BigInteger rank;
    private int points;
    private int win;
    private int lose;
    private int draw;
    private int numberOfMatch;
    private int homeGoal;
    private int awayGoal;

    public ListClasement(){}

    public ListClasement(BigInteger id, String teamName, String city, BigInteger rank, int points, int win, int lose, int draw, int numberOfMatch, int homeGoal, int awayGoal) {
        this.id = id;
        this.teamName = teamName;
        this.city = city;
        this.rank = rank;
        this.points = points;
        this.win = win;
        this.lose = lose;
        this.draw = draw;
        this.numberOfMatch = numberOfMatch;
        this.homeGoal = homeGoal;
        this.awayGoal = awayGoal;
    }

    public ListClasement setListClasement(Object[] clasement) {
        this.id = (BigInteger) clasement[0];
        this.awayGoal = (int) clasement[1];
        this.city = (String) clasement[2];
        this.draw = (int) clasement[4];
        this.homeGoal = (int) clasement[5];
        this.lose = (int) clasement[6];
        this.numberOfMatch = (int) clasement[7];
        this.points = (int) clasement[8];
        this.teamName = (String) clasement[9];
        this.win = (int) clasement[11];
        this.rank = (BigInteger) clasement[12];

        return this;
    }
}

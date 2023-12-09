package com.application.clasementAPI.pojos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Setter
@Getter
public class ListTeams {
    private BigInteger id;
    private String name;
    private String city;

    public ListTeams() {}

    public ListTeams(BigInteger id, String teamName, String city) {
        this.id = id;
        this.name = teamName;
        this.city = city;
    }

    public ListTeams setListTeams(Object[] team) {
        this.id = (BigInteger) team[0];
        this.name = (String) team[1];
        this.city = (String) team[2];

        return this;
    }
}

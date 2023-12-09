package com.application.clasementAPI.pojos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
public class FinishUpdateMatch {
    @NotNull(message = "Nilai tidak boleh kosong")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Nilai harus berupa angka")
    @Min(value = 1, message = "Home team id tidak valid")
    private Long matchId;

    @Min(value = 0, message = "homeTeamGoal harus lebih besar atau sama dengan 0")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "homeTeamGoal harus berupa angka")
    private int homeTeamGoal;

    @Min(value = 0, message = "awayTeamGoal harus lebih besar atau sama dengan 0")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "awayTeamGoal harus berupa angka")
    private int awayTeamGoal;
}

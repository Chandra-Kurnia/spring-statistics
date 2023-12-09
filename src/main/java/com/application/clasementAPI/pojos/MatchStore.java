package com.application.clasementAPI.pojos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
public class MatchStore {

    @NotNull(message = "Nilai tidak boleh kosong")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Nilai harus berupa angka")
    @Min(value = 1, message = "Home team id tidak valid")
    private Long home_team;

    @NotNull(message = "Nilai tidak boleh kosong")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Nilai harus berupa angka")
    @Min(value = 1, message = "Away team id tidak valid")
    private Long away_team;

    @NotNull(message = "Tanggal tidak boleh kosong")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future(message = "Tanggal harus setelah hari ini")
    private LocalDate scheduled;
}

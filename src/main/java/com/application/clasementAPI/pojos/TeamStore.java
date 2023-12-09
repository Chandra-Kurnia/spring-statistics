package com.application.clasementAPI.pojos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class TeamStore {
    @NotNull(message = "Nama tim tidak boleh kosong")
    private String teamName;

    @NotNull(message = "Kota asal tidak boleh kosong")
    private String city;
}

package com.dispensersystem.mfds.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DispenseFuelByLiterResponse {
    private String name;
    private double litersBought;
    private double amount;
}

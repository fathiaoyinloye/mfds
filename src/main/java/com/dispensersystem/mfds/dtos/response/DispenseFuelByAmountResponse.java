package com.dispensersystem.mfds.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DispenseFuelByAmountResponse {
    private String name;
    private double pricePerLiter;
    private double litersBought;
    private double amount;

}

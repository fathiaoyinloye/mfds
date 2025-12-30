package com.dispensersystem.mfds.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AddFuelResponse {
    private String name;
    private double pricePerLiter;
    private double quantityToBeStocked;
}

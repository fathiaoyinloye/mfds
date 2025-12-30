package com.dispensersystem.mfds.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetAvailableFuelResponse {
    private Long fuelId;
    private String name;
    private double pricePerLiter;
    private double quantityToBeStocked;

}

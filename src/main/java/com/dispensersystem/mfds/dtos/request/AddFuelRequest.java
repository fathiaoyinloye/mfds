package com.dispensersystem.mfds.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AddFuelRequest {
    private String name;
    private double pricePerLiter;
    private double quantityAvailable;
}

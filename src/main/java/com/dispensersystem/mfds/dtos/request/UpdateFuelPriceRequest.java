package com.dispensersystem.mfds.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateFuelPriceRequest {
    private String fuelName;
    private double newPrice;
}

package com.dispensersystem.mfds.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class RestockFuelResponse {
    private String fuelName;
    private double newQuantity;
}

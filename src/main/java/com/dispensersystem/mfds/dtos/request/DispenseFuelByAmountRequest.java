package com.dispensersystem.mfds.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DispenseFuelByAmountRequest {
    private String name;
    private double amount;
}

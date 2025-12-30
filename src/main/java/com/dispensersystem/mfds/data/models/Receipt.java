package com.dispensersystem.mfds.data.models;


import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String fuelStationName;
    private Long receiptId;
    private String attendantName;
    private  double pricePerLiter;
    private double literBought;
    private double price;
    private LocalDateTime timeCreated;



}

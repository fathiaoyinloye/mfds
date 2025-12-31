package com.dispensersystem.mfds.data.models;


import jakarta.persistence.*;

import lombok.*;



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
    private Long receiptId;
    private String fuelStationName;
    private String attendantName;
    private  double pricePerLiter;
    private double literBought;
    private double price;
    private String timeCreated;



}

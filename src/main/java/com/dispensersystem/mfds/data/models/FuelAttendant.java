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
@Table(name = "fuel_attendant")
public class FuelAttendant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fuelAttendantId;
    private String name;

}

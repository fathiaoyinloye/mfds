package com.dispensersystem.mfds.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "fuelstation")
public class FuelStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fuelStationId;
    private String name;
    @OneToOne
    private Admin admin;
    @OneToMany
    @MapKey(name = "name")
    @JoinColumn(name = "fuelStationId")
    private Map<String, Fuel> fuels = new HashMap<>();
    @OneToMany
    @JoinColumn(name = "fuelAttendants")
    private List<FuelAttendant> fuelAttendants = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "receipts")
    private List<Receipt> receipts = new ArrayList<>();

}

package com.dispensersystem.mfds.data.repositories;

import com.dispensersystem.mfds.data.models.Fuel;
import com.dispensersystem.mfds.data.models.FuelAttendant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelAttendantRepository extends JpaRepository <FuelAttendant, Long> {
    FuelAttendant findByName(String name);
}

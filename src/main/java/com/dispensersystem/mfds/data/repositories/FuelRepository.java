package com.dispensersystem.mfds.data.repositories;

import com.dispensersystem.mfds.data.models.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelRepository extends JpaRepository <Fuel, Long> {
    Fuel findByName(String name);

}

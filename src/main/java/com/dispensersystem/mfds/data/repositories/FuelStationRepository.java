package com.dispensersystem.mfds.data.repositories;

import com.dispensersystem.mfds.data.models.FuelStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelStationRepository extends JpaRepository<FuelStation, Long> {
}

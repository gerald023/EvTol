package com.eVTOL.repository;

import com.eVTOL.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    boolean existsBySerialNumber(int serialNumber);
    Boolean existsByName(String name);
}

package com.eVTOL.service;

import com.eVTOL.dto.VehicleDTO;
import com.eVTOL.enums.Model;
import com.eVTOL.model.Vehicle;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AuthService {
    public String addEvtol(VehicleDTO vehicleDTO);
    public List<Vehicle> getAllEvtol( );
    public String updateByID(Long id, Vehicle vehicle);
    public List<Vehicle> getOneVehicle(Long id);
    public String deleteEvtolByID(Long id);
    public String updateModel(Long id, int checker);
    public void chargeBattery(Long id, Vehicle battery);
    public String updateState(Long id, Vehicle vehicle);
    public void updateBattery();
}

package com.eVTOL.controller;

import com.eVTOL.dto.VehicleDTO;
import com.eVTOL.enums.Model;
import com.eVTOL.model.Vehicle;
import com.eVTOL.service.implimentation.AuthImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evtol")
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthImplementation authImplementation;

    public AuthController(AuthImplementation authImplementation) {
        this.authImplementation = authImplementation;
    }
    @PostMapping
    public ResponseEntity<String> addEvtol(@RequestBody VehicleDTO vehicleDTO){
            String register = authImplementation.addEvtol(vehicleDTO);
        return new ResponseEntity<>(register, HttpStatus.CREATED);
    }
    @GetMapping
    public List<Vehicle> getAllEvtol(){
        return authImplementation.getAllEvtol();
    }
    @GetMapping("/{id}")
    public List<Vehicle> getOneVehicle(@PathVariable Long id){
      return   authImplementation.getOneVehicle(id);
    }
    @DeleteMapping("/{id}")
    public String deleteEvtolByID(@PathVariable Long id){
        authImplementation.deleteEvtolByID(id);
        return "vehicle has been deleted";
    }
    @PutMapping("/{id}")
    public String updatebyID (@PathVariable Long id, Vehicle vehicle){
        authImplementation.updateByID(id, vehicle);
        return "vehicle with the id of " + id + " has been updated";
    }
    @PatchMapping("/{id}/{checker}")
    public String updateModel(@PathVariable Long id, @PathVariable int checker){
        authImplementation.updateModel(id, checker);
        return "patched successfully";
    }
//    @PutMapping("/state/{id}")
//    public String updateState(@PathVariable Long id, Vehicle vehicle){
//        authImplementation.updateState(id, vehicle);
//        return "vehicle state has been updated";
//    }
//    @PutMapping("/battery/{id}")
//    public String chargeBattery(@PathVariable Long id, Vehicle battery){
//        authImplementation.chargeBattery(id, battery);
//        return "battery is charge";
//    }
}

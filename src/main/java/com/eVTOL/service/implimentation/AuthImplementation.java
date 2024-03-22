package com.eVTOL.service.implimentation;

import com.eVTOL.dto.VehicleDTO;
import com.eVTOL.enums.Model;
import com.eVTOL.enums.State;
import com.eVTOL.exceptions.EvtolException;
import com.eVTOL.model.Vehicle;
import com.eVTOL.repository.VehicleRepository;
import com.eVTOL.service.AuthService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthImplementation implements AuthService {
    private final VehicleRepository vehicleRepository;

    public AuthImplementation(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public String addEvtol(VehicleDTO vehicleDTO) {
        if (vehicleRepository.existsBySerialNumber(vehicleDTO.getSerialNumber())){
            throw new EvtolException(HttpStatus.BAD_REQUEST, "Serial number already exists");
        } else if (vehicleRepository.existsByName(vehicleDTO.getName())) {
            throw new EvtolException(HttpStatus.BAD_REQUEST, "name already exists");
        } else {
            Vehicle vehicle = new Vehicle();
            vehicle.setWeight(vehicleDTO.getWeight());
            vehicle.setName(vehicleDTO.getName());
            vehicle.setBattery(100);
            vehicle.setSerialNumber(vehicleDTO.getSerialNumber());
            vehicle.setState(State.IDLE);
            vehicle.setModel(Model.Heavyweight);
            vehicleRepository.save(vehicle);
        }
        return "Vehicle has been registered!";
    }

    @Override
    public List<Vehicle> getAllEvtol() {

        return vehicleRepository.findAll();
    }

    @Override
    public String updateByID(@PathVariable Long id, Vehicle vehicle) {
        Vehicle data = vehicleRepository.findById(vehicle.getId()).orElse(null);
        if (data != null){
            data.setWeight(vehicle.getWeight());
            data.setBattery(vehicle.getBattery());
            data.setState(vehicle.getState());
            data.setSerialNumber(vehicle.getSerialNumber());
            data.setModel(vehicle.getModel());
            vehicleRepository.save(data);
        }
        return "eVTOL with the serial number of " + vehicle.getSerialNumber() + " has been updated";
    }

    @Override
    public List<Vehicle> getOneVehicle(@PathVariable Long id) {

        return vehicleRepository.findAllById(Collections.singleton(id));
    }

    @Override
    public String deleteEvtolByID(Long id) {
        vehicleRepository.deleteById(id);
        return "vehicle has been deleted";
    }

    @Override
    public String updateModel(@PathVariable Long id, int checker) {
        Vehicle evtol = vehicleRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("model not fount"));
//        evtol.setModel(model);
        //        if (model. == Model.Heavyweight && evtol != null){
//            evtol.setModel(Model.Heavyweight);
//        }
//        if (model == "light" && vehicle != null){
//            evtol.setModel(Model.LIGHTWEIGHT);
//        }
//        if (model == "middle" && vehicle != null){
//            evtol.setModel(Model.Middleweight);
//        }
//        if (model == "cruise" && vehicle != null){
//            evtol.setModel(Model.CruiserWeight);
//        }
//        switch (evtol.getModel()){
//            case Heavyweight:
//                evtol.setModel(Model.LIGHTWEIGHT);
//                break;
//            case LIGHTWEIGHT:
//                evtol.setModel(Model.Heavyweight);
//                break;
//            case Middleweight:
//                evtol.setModel(Model.CruiserWeight);
//                break;
//            case CruiserWeight:
//                evtol.setModel(Model.Middleweight);
//        }
        switch (checker){
            case 1:
                evtol.setModel(Model.Heavyweight);
                break;
            case 2:
                evtol.setModel(Model.LIGHTWEIGHT);
                break;
            case 3:
                evtol.setModel(Model.Middleweight);
                break;
            case 4:
                evtol.setModel(Model.CruiserWeight);
                break;
        }
         vehicleRepository.save(evtol);
        return "Model updated";
    }

    @Override
    public void chargeBattery(@PathVariable Long id, Vehicle battery) {
        Vehicle vehicle = vehicleRepository.findById(battery.getId()).orElse(null);
       if (vehicle != null){
           vehicle.setBattery(battery.getBattery());
           vehicleRepository.save(vehicle);
       }
    }

    @Override
    public String updateState(@PathVariable Long id, Vehicle vehicle) {
        Vehicle stateOption = vehicleRepository.findById(vehicle.getId()).orElse(null);
        if (stateOption != null){
            stateOption.setState(vehicle.getState());
            vehicleRepository.save(stateOption);
        }
        return "state has been updated";
    }

    @Override
    @Scheduled(fixedRate = 100_000L)
    public void updateBattery() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        Random random = new Random();
        int MAX = 5;
        int MIN = 1;
        for (Vehicle vehicle : vehicles){
            vehicle.setBattery(Math.max(vehicle.getBattery() - random.nextInt(MAX - MIN + 1), 0));
            if (vehicle.getBattery() > 30){
                vehicle.setState(State.LOADING);
            }else{
                vehicle.setState(State.IDLE);
            }
            if (vehicle.getBattery() == 0){
                vehicle.setBattery(100);
                vehicle.setState(State.LOADING);
            }
            vehicleRepository.save(vehicle);
        }
    }

}

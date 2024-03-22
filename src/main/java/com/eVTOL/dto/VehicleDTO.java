package com.eVTOL.dto;

import com.eVTOL.enums.Model;
import com.eVTOL.enums.State;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class VehicleDTO {
    private int serialNumber;
    private String name;
    @Enumerated(EnumType.STRING)
    private Model model;
    private int weight;
    private int battery;
    @Enumerated(EnumType.STRING)
    private State state;
}

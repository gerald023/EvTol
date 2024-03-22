package com.eVTOL.model;

import com.eVTOL.enums.Model;
import com.eVTOL.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "eVTOL")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int serialNumber;
    private String name;
    @Enumerated (EnumType.STRING)
    private Model model;
    private int weight;
    private int battery;
    @Enumerated(EnumType.STRING)
    private State state;
}

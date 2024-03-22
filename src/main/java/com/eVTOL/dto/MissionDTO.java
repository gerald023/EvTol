package com.eVTOL.dto;

import com.eVTOL.model.Product;
import com.eVTOL.model.Vehicle;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionDTO {
    private int distance;
    private Date date;
}

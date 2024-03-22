package com.eVTOL.dto;

import com.eVTOL.enums.RequestState;
import com.eVTOL.model.Product;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
public class RequestDTO {
    private String name;
    private String pickUp;
    @Enumerated(EnumType.STRING)
    private RequestState requestState;
    private String dropZOne;
    private int time;
    private int price;
}

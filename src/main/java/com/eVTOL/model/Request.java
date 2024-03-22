package com.eVTOL.model;

import com.eVTOL.enums.RequestState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pickUp;
    @Enumerated(EnumType.STRING)
    private RequestState requestState;
    private String dropZOne;
    private int time;
    private int price;

    @ManyToMany
    @JoinTable(name = "request_product",
    joinColumns = @JoinColumn(name = "request_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )

    private Set<Product> assignProducts = new HashSet<>();
}

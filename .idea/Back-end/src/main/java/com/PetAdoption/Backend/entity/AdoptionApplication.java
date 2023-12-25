package com.PetAdoption.Backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "AdoptionApplications")
public class AdoptionApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "adopterEmail")
    private Adopter adopter;
    @ManyToOne
    @JoinColumn(name = "petId")
    private Pet pet;
    private String status;
}

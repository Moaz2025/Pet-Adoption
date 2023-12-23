package com.PetAdoption.Backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "shelterName")
    private Shelter shelter;
    private String species;
    private String breed;
    private boolean isBooked;
    private float age;
    private String behavior;
    private String description;
    private boolean houseTraining;
    private String gender;
    private String vaccination;
    private String spaying;
    private String neutering;
}

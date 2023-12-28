package com.PetAdoption.Backend.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class petAddFormDTO {

    private String name;
    private String species;
    private String breed;
    private float age;
    private String behavior;
    private String description;
    private boolean houseTraining;
    private String gender;
    private String vaccination;
    private String spaying;
    private String neutering;
}

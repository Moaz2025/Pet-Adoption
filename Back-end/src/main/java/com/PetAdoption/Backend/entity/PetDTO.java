package com.PetAdoption.Backend.entity;


import lombok.Data;

@Data
public class PetDTO {
    private int id;
    private String name;
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

package com.PetAdoption.Backend.entity;


import lombok.Data;
import lombok.Setter;

@Data
public class PetResponse {
    private int id;
    private String name;
    private String shelterName; //edited
    private String species;
    private String breed;
    private Boolean isBooked;
    private float age;
    private String behavior;
    private String description;
    private boolean houseTraining;
    private String gender;
    private String vaccination;
    private String spaying;
    private String neutering;

}

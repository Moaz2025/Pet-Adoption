package com.PetAdoption.Backend.entity;

import lombok.Data;

@Data
public class AdoptionApplicationDTO {
    int appId;
    String adopterEmail;
    int petId;
    String status;

}

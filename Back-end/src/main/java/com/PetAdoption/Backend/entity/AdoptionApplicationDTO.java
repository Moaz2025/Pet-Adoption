package com.PetAdoption.Backend.entity;

import lombok.Data;

@Data
public class AdoptionApplicationDTO {
    String adopterEmail;
    int petId;
    String status;

}

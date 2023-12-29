package com.PetAdoption.Backend.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;

@Data
public class ApplicationResponse {
    String adopterEmail ;
    int petId;
    boolean status;
}

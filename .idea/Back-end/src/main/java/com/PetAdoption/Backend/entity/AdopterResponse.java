package com.PetAdoption.Backend.entity;

import lombok.Data;

import java.util.List;

@Data
public class AdopterResponse {
    private String message;
    private List<AdopterAttributes> adoptersList;
}

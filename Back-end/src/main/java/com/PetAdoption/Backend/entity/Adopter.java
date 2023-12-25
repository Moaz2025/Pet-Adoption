package com.PetAdoption.Backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Adopters")
public class Adopter {

    @Id
    private String email;
    private String name;
    private String password;
    private String salt;
    private String phone;
    private String token;
}

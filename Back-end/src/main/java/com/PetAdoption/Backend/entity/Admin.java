package com.PetAdoption.Backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Admins")
public class Admin {

    @Id
    private String email;
    private String name;
    private String password;
    private String salt;
    private String token;
}

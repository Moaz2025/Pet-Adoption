package com.PetAdoption.Backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Managers")
public class Manager {

    @Id
    private String email;
    private String name;
    private String password;
    private String salt;
    private String phone;
    @OneToOne
    @JoinColumn(name = "shelterName")
    private Shelter shelter;
}

package com.PetAdoption.Backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Staff")
public class Staff {

    @Id
    private String email;
    private String name;
    private String password;
    private String salt;
    private String phone;
    private String role;
    @ManyToOne
    @JoinColumn(name = "shelterName")
    private Shelter shelter;
}

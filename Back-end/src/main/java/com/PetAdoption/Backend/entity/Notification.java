package com.PetAdoption.Backend.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String content;
    @ManyToOne
    @JoinColumn(name = "adopterEmail")
    Adopter adopter;
    @ManyToOne
    @JoinColumn(name = "petId")
    Pet pet;

}

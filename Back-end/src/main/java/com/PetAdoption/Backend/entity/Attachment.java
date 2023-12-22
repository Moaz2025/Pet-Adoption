package com.PetAdoption.Backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Attachments")
public class Attachment {

    @Id
    @ManyToOne
    @JoinColumn(name = "petId")
    private Pet pet;
    private String attachment;
}

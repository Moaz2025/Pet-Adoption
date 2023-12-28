package com.PetAdoption.Backend.repository;

import com.PetAdoption.Backend.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findByShelter_Name(String shelter_name);


}


package com.PetAdoption.Backend.repository;

import com.PetAdoption.Backend.entity.Adopter;
import com.PetAdoption.Backend.entity.AdoptionApplication;
import com.PetAdoption.Backend.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface AdoptionApplicationRepository extends JpaRepository<AdoptionApplication, Integer> {
    List<AdoptionApplication> findByPet_Id(int id);
    AdoptionApplication   findById(int id);
    List<AdoptionApplication> findAllByAdopter(Adopter adopter);

    List<AdoptionApplication> findByPetAndAdopter(Pet pet ,Adopter adopter);

}

package com.PetAdoption.Backend.repository;

import com.PetAdoption.Backend.entity.Pet;
import com.PetAdoption.Backend.entity.PetDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    // I implemented them all as in the report say Like
    List<Pet> findByNameLikeIgnoreCase(String name);
    List<Pet> findByAge(Float age);
    List<Pet> findByBehaviorLikeIgnoreCase(String behavior);
    List<Pet> findByBreedLikeIgnoreCase(String breed);
    List<Pet> findByGenderLikeIgnoreCase(String gender);
    List<Pet> findByHouseTraining(Boolean houseTraining);
    List<Pet> findByNeuteringLikeIgnoreCase(String neutering);
    List<Pet> findBySpayingLikeIgnoreCase(String spaying);
    List<Pet> findBySpeciesLikeIgnoreCase(String species);
    List<Pet> findByVaccinationLikeIgnoreCase(String vaccination);
    List<Pet> findByShelterNameLikeIgnoreCase(String shelterName);
}

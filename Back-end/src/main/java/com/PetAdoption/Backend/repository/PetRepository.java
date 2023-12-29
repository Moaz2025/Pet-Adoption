package com.PetAdoption.Backend.repository;

import com.PetAdoption.Backend.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    // I implemented them all as in the report say Contains
//    default Pet findById(Integer petId) {
//        return null;
//    }

    List<Pet> findByNameContainsIgnoreCase(String name);
    List<Pet> findByAgeLessThan(Float age);
    List<Pet> findByBehaviorContainsIgnoreCase(String behavior);
    List<Pet> findByBreedContainsIgnoreCase(String breed);
    List<Pet> findByGenderContainsIgnoreCase(String gender);
    List<Pet> findByHouseTraining(Boolean houseTraining);
    List<Pet> findByNeuteringContainsIgnoreCase(String neutering);
    List<Pet> findBySpayingContainsIgnoreCase(String spaying);
    List<Pet> findBySpeciesContainsIgnoreCase(String species);
    List<Pet> findByVaccinationContainsIgnoreCase(String vaccination);
    List<Pet> findByShelterNameContainsIgnoreCase(String shelterName);
}

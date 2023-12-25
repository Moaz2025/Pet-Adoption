package com.PetAdoption.Backend.service;

import com.PetAdoption.Backend.entity.Pet;
import com.PetAdoption.Backend.entity.PetDTO;
import com.PetAdoption.Backend.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    // viewing the pet

    public Pet findPetProfileById(Integer id){
        if(id == null) return null;

        return petRepository.getById(id);
    }
    public List<Pet> findAllPets(){return petRepository.findAll();}
    public List<Pet> findPetByName(String name){
        if(name == null) return Collections.emptyList();
        return petRepository.findByNameLikeIgnoreCase(name);
    }

    //Searching by all valid attributes
    public List findByBehavior(String behavior){
        // to avoid null pointer exception
        if(behavior == null) return Collections.emptyList();
        return petRepository.findByBehaviorLikeIgnoreCase(behavior);
    }
    public List findByBreed(String breed){
        if(breed == null) return Collections.emptyList();
        return petRepository.findByBreedLikeIgnoreCase(breed);
    }
    public List findByGender(String gender){
        if(gender == null) return Collections.emptyList();
        return petRepository.findByGenderLikeIgnoreCase(gender);
    }
    public List findByHouseTraining(Boolean houseTraining){
        if(houseTraining == null) return Collections.emptyList();
        return petRepository.findByHouseTraining(houseTraining);
    }
    public List findByNeutering(String neutering){
        if(neutering == null) return Collections.emptyList();
        return petRepository.findByNeuteringLikeIgnoreCase(neutering);
    }
    public List findBySpaying(String spaying){
        if(spaying == null) return Collections.emptyList();
        return petRepository.findBySpayingLikeIgnoreCase(spaying);
    }
    public List<Pet> findBySpecies(String species){
        if(species == null) return Collections.emptyList();
        return petRepository.findBySpeciesLikeIgnoreCase(species);
    }
    public List<Pet> findByVaccination(String vaccination){
        if(vaccination == null) return Collections.emptyList();
        return petRepository.findByVaccinationLikeIgnoreCase(vaccination);
    }
    public List<Pet> findByShelterName(String shelterName){
        if(shelterName == null) return Collections.emptyList();
        return petRepository.findByShelterNameLikeIgnoreCase(shelterName);
    }

    public List<Pet> findByAge(Float age){
        if(age == null) return Collections.emptyList();
        return petRepository.findByAge(age);
    }
    public PetDTO convertToPetDTO(Pet pet){
        if(pet == null) return null;
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setAge(pet.getAge());
        petDTO.setBehavior(pet.getBehavior());
        petDTO.setBreed(pet.getBreed());
        petDTO.setDescription(pet.getDescription());
        petDTO.setBooked(pet.isBooked());
        petDTO.setGender(pet.getGender());
        petDTO.setName(pet.getName());
        petDTO.setShelter(pet.getShelter());
        petDTO.setNeutering(pet.getNeutering());
        petDTO.setSpaying(pet.getSpaying());
        petDTO.setSpecies(pet.getSpecies());
        petDTO.setHouseTraining(pet.isHouseTraining());
        petDTO.setVaccination(pet.getVaccination());
        return petDTO;
    }
}

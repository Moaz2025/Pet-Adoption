package com.PetAdoption.Backend.service;

import com.PetAdoption.Backend.entity.Pet;
import com.PetAdoption.Backend.entity.PetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PetService {
    @Autowired
    private com.PetAdoption.Backend.repository.PetRepository petRepository;
    // viewing the pet

    public Pet findPetProfileById(Integer id){
        if(id == null) return null;

        return petRepository.getById(id);
    }
    public List<Pet> findAllPets(){return petRepository.findAll();}
    public List<Pet> findPetByName(String name){
        if(name == null) return Collections.emptyList();
        return petRepository.findByNameContainsIgnoreCase(name);
    }

    //Searching by all valid attributes
    public List findByBehavior(String behavior){
        // to avoid null pointer exception
        if(behavior == null) return Collections.emptyList();
        return petRepository.findByBehaviorContainsIgnoreCase(behavior);
    }
    public List findByBreed(String breed){
        if(breed == null) return Collections.emptyList();
        return petRepository.findByBreedContainsIgnoreCase(breed);
    }
    public List findByGender(String gender){
        if(gender == null) return Collections.emptyList();
        return petRepository.findByGenderContainsIgnoreCase(gender);
    }
    public List findByHouseTraining(Boolean houseTraining){
        if(houseTraining == null) return Collections.emptyList();
        return petRepository.findByHouseTraining(houseTraining);
    }
    public List findByNeutering(String neutering){
        if(neutering == null) return Collections.emptyList();
        return petRepository.findByNeuteringContainsIgnoreCase(neutering);
    }
    public List findBySpaying(String spaying){
        if(spaying == null) return Collections.emptyList();
        return petRepository.findBySpayingContainsIgnoreCase(spaying);
    }
    public List<Pet> findBySpecies(String species){
        if(species == null) return Collections.emptyList();
        return petRepository.findBySpeciesContainsIgnoreCase(species);
    }
    public List<Pet> findByVaccination(String vaccination){
        if(vaccination == null) return Collections.emptyList();
        return petRepository.findByVaccinationContainsIgnoreCase(vaccination);
    }
    public List<Pet> findByShelterName(String shelterName){
        if(shelterName == null) return Collections.emptyList();
        return petRepository.findByShelterNameContainsIgnoreCase(shelterName);
    }

    public List<Pet> findByAgeLessThan(Float age){
        if(age == null) return Collections.emptyList();
        return petRepository.findByAgeLessThan(age);
    }
    public PetResponse convertToPetResponse(Pet pet){
        if(pet == null) return null;
        PetResponse petResponse = new PetResponse();
        petResponse.setId(pet.getId());
        if(petResponse.getId() == 0)return null;
        petResponse.setAge(pet.getAge());
        petResponse.setBehavior(pet.getBehavior());
        petResponse.setBreed(pet.getBreed());
        petResponse.setDescription(pet.getDescription());
        petResponse.setBooked(pet.isBooked());
        petResponse.setGender(pet.getGender());
        petResponse.setName(pet.getName());
        if(pet.getShelter() == null)
            {petResponse.setShelterName(null);}
        else
            {petResponse.setShelterName(pet.getShelter().getName());}
        petResponse.setNeutering(pet.getNeutering());
        petResponse.setSpaying(pet.getSpaying());
        petResponse.setSpecies(pet.getSpecies());
        petResponse.setHouseTraining(pet.isHouseTraining());
        petResponse.setVaccination(pet.getVaccination());
        return petResponse;
    }
    public List<PetResponse> convertToPetResponseList(List<Pet> pets){
        List<Pet> petList = pets;
        List<PetResponse> petResponseList = new ArrayList<>();
        for(Pet pet : petList){
            petResponseList.add(convertToPetResponse(pet));
        }
        return petResponseList;
    }
}

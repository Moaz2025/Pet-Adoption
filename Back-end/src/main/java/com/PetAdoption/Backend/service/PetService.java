package com.PetAdoption.Backend.service;

import com.PetAdoption.Backend.entity.Pet;
import com.PetAdoption.Backend.entity.PetResponse;
import com.PetAdoption.Backend.entity.Shelter;
import com.PetAdoption.Backend.entity.petAddFormDTO;
import com.PetAdoption.Backend.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    PetRepository pr;
    @Autowired
    PetRepository petRepository;
    @Autowired
    ShelterService ss;

    public void addPetProfile(petAddFormDTO form, Shelter shelter){
        Pet pet =new Pet();

        pet.setBooked(false);
        pet.setAge(form.getAge());
        pet.setBreed(form.getBreed());
        pet.setGender(form.getGender());
        pet.setBehavior(form.getBehavior());
        pet.setDescription(form.getDescription());
        pet.setName(form.getName());
        pet.setHouseTraining(form.isHouseTraining());
        pet.setNeutering(form.getNeutering());
        pet.setSpecies(form.getSpecies());
        pet.setVaccination(form.getVaccination());
        pet.setSpaying(form.getSpaying());
        pet.setShelter(shelter);
        pr.save(pet);





    }
    public boolean editPetProfile(petAddFormDTO form, int id){
        Optional<Pet> pet = pr.findById(id);
        if(pet.get().isBooked()) {
            return false;

        }


        pet.get().setAge(form.getAge());
        pet.get().setBreed(form.getBreed());
        pet.get().setGender(form.getGender());
        pet.get().setBehavior(form.getBehavior());
        pet.get().setDescription(form.getDescription());
        pet.get().setName(form.getName());
        pet.get().setHouseTraining(form.isHouseTraining());
        pet.get().setNeutering(form.getNeutering());
        pet.get().setSpecies(form.getSpecies());
        pet.get().setVaccination(form.getVaccination());
        pet.get().setSpaying(form.getSpaying());
        pr.save(pet.get());
        return true;





    }

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

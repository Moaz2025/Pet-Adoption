package com.PetAdoption.Backend.controller;

import com.PetAdoption.Backend.entity.Pet;
import com.PetAdoption.Backend.entity.PetResponse;
import com.PetAdoption.Backend.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Pet")
@CrossOrigin(origins = "http://localhost:3000")
public class PetController {
    @Autowired
    PetService petService;


    @GetMapping("/getAllPets")
    public List<PetResponse> getAllPets(){
        return petService.convertToPetResponseList(petService.findAllPets());
    }
    @Transactional
    @GetMapping("/getPetById")
    public PetResponse getPetById(@RequestParam(name="id") Integer id){
        if(id == null)return null;
        PetResponse petResponse;
        Pet pet;
        try {
            // use pet
            pet = petService.findPetProfileById(id);
//            System.out.println(pet.getId());
            if(pet == null) return null;
            petResponse = petService.convertToPetResponse(pet);
        }catch (EntityNotFoundException e){
            System.out.println(e);
            return null;
        }

        return petResponse;
    }



    @GetMapping("/getPetByName")
    public List<PetResponse> getPetByName(@RequestParam(name="name") String name){
        return petService.convertToPetResponseList(petService.findPetByName(name));
    }

    @GetMapping("/getPetByBehavior")
    public List<PetResponse> getPetByBehavior(@RequestParam(name="behavior") String behavior){
        return petService.convertToPetResponseList(petService.findByBehavior(behavior));
    }
    @GetMapping("/getPetByBreed")
    public List<PetResponse> getPetByBreed(@RequestParam(name="breed") String breed){
        return petService.convertToPetResponseList(petService.findByBreed(breed));
    }
    @GetMapping("/getPetByGender")
    public List<PetResponse> getPetByGender(@RequestParam(name="gender") String gender){
        return petService.convertToPetResponseList(petService.findByGender(gender));
    }
    @GetMapping("/getPetByHouseTraining")
    public List<PetResponse> getPetByHouseTraining(@RequestParam(name="houseTraining") Boolean houseTraining){
        return petService.convertToPetResponseList(petService.findByHouseTraining(houseTraining));
    }
    @GetMapping("/getPetByNeutering")
    public List<PetResponse> getPetByNeutering(@RequestParam(name="neutering") String neutering){
        return petService.convertToPetResponseList(petService.findByNeutering(neutering));
    }
    @GetMapping("/getPetBySpaying")
    public List<PetResponse> getPetBySpaying(@RequestParam(name="spaying") String spaying){
        return petService.convertToPetResponseList(petService.findBySpaying(spaying));
    }
    @GetMapping("/getPetBySpecies")
    public List<PetResponse> getPetBySpecies(@RequestParam(name="species") String species){
        return petService.convertToPetResponseList(petService.findBySpecies(species));
    }

    @GetMapping("/getPetByVaccination")
    public List<PetResponse> getPetByVaccination(@RequestParam(name="vaccination") String vaccination){
        return petService.convertToPetResponseList(petService.findByVaccination(vaccination));
    }
    @GetMapping("/getPetByShelter")
    public List<PetResponse> getPetByShelter(@RequestParam(name="shelter") String shelter){
        return petService.convertToPetResponseList(petService.findByShelterName(shelter));
    }
    @GetMapping("/getPetByAge")
    public List<PetResponse> getPetByAge(@RequestParam(name="age") Float age){
        return petService.convertToPetResponseList(petService.findByAgeLessThan(age));
    }
}

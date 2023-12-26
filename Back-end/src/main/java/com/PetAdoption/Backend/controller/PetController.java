package com.PetAdoption.Backend.controller;

import com.PetAdoption.Backend.entity.Pet;
import com.PetAdoption.Backend.entity.PetDTO;
import com.PetAdoption.Backend.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.swing.text.html.parser.Entity;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/Pet")
@CrossOrigin(origins = "http://localhost:3000")
public class PetController {
    @Autowired
    PetService petService;


    @GetMapping("/getAllPets")
    public List<Pet> getAllPets(){
        return petService.findAllPets();
    }
    @Transactional
    @GetMapping("/getPetById")
    public PetDTO getPetById(@RequestParam(name="id") Integer id){
        if(id == null)return null;
        PetDTO petDTO ;
        Pet pet;
        try {
            // use pet
            pet = petService.findPetProfileById(id);
            System.out.println(pet.getId());
            if(pet == null) return null;
            petDTO = petService.convertToPetDTO(pet);
        }catch (EntityNotFoundException e){
            System.out.println(e);
            return null;
        }

        return petDTO;
    }



    @GetMapping("/getPetByName")
    public List<Pet> getPetByName(@RequestParam(name="name") String name){
        return petService.findPetByName(name);
    }

    @GetMapping("getPetByBehavior")
    public List<Pet> getPetByBehavior(@RequestParam(name="behavior") String behavior){
        return petService.findByBehavior(behavior);
    }
    @GetMapping("/getPetByBreed")
    public List<Pet> getPetByBreed(@RequestParam(name="breed") String breed){
        return petService.findByBreed(breed);
    }
    @GetMapping("/getPetByGender")
    public List<Pet> getPetByGender(@RequestParam(name="gender") String gender){
        return petService.findByGender(gender);
    }
    @GetMapping("/getPetByHouseTraining")
    public List<Pet> getPetByHouseTraining(@RequestParam(name="houseTraining") Boolean houseTraining){
        return petService.findByHouseTraining(houseTraining);
    }
    @GetMapping("getPetByNeutering")
    public List<Pet> getPetByNeutering(@RequestParam(name="neutering") String neutering){
        return petService.findByNeutering(neutering);
    }
    @GetMapping("/getPetBySpaying")
    public List<Pet> getPetBySpaying(@RequestParam(name="spaying") String spaying){
        return petService.findBySpaying(spaying);
    }
    @GetMapping("/getPetBySpecies")
    public List<Pet> getPetBySpecies(@RequestParam(name="species") String species){
        return petService.findBySpecies(species);
    }

    @GetMapping("/getPetByVaccination")
    public List<Pet> getPetByVaccination(@RequestParam(name="vaccination") String vaccination){
        return petService.findByVaccination(vaccination);
    }
    @GetMapping("/getPetByShelter")
    public List<Pet> getPetByShelter(@RequestParam(name="shelter") String shelter){
        return petService.findByShelterName(shelter);
    }
    @GetMapping("/getPetByAge")
    public List<Pet> getPetByAge(@RequestParam(name="age") Float age){
        return petService.findByAgeLessThan(age);
    }
}

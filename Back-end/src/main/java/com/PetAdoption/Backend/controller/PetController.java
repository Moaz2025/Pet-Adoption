package com.PetAdoption.Backend.controller;

import com.PetAdoption.Backend.entity.Pet;
import com.PetAdoption.Backend.entity.PetDTO;
import com.PetAdoption.Backend.service.PetService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public PetDTO getPetById(@PathVariable Integer id){
        if(id == null)return null;
        PetDTO petDTO = new PetDTO();
        Pet pet = new Pet();
        pet = petService.findPetProfileById(id);
        if(pet == null) return null;
        petDTO = petService.convertToPetDTO(pet);
        return petDTO;
    }



    @GetMapping("/name={name}")
    public List<Pet> getPetByName(@PathVariable String name){
        return petService.findPetByName(name);
    }

    @GetMapping("/behavior={behavior}")
    public List<Pet> getPetByBehavior(@PathVariable String behavior){
        return petService.findByBehavior(behavior);
    }
    @GetMapping("/breed={breed}")
    public List<Pet> getPetByBreed(@PathVariable String breed){
        return petService.findByBreed(breed);
    }
    @GetMapping("/gender={gender}")
    public List<Pet> getPetByGender(@PathVariable String gender){
        return petService.findByGender(gender);
    }
    @GetMapping("/houseTraining={houseTraining}")
    public List<Pet> getPetByHouseTraining(@PathVariable Boolean houseTraining){
        return petService.findByHouseTraining(houseTraining);
    }
    @GetMapping("/neutering={neutering}")
    public List<Pet> getPetByNeutering(@PathVariable String neutering){
        return petService.findByNeutering(neutering);
    }
    @GetMapping("/spaying={spaying}")
    public List<Pet> getPetBySpaying(@PathVariable String spaying){
        return petService.findBySpaying(spaying);
    }
    @GetMapping("/species={species}")
    public List<Pet> getPetBySpecies(@PathVariable String species){
        return petService.findBySpecies(species);
    }

    @GetMapping("/vaccination={vaccination}")
    public List<Pet> getPetByVaccination(@PathVariable String vaccination){
        return petService.findByVaccination(vaccination);
    }
    @GetMapping("/shelter={shelter}")
    public List<Pet> getPetByShelter(@PathVariable String shelter){
        return petService.findByShelterName(shelter);
    }
    @GetMapping("/age={age}")
    public List<Pet> getPetByAge(@PathVariable Float age){
        return petService.findByAge(age);
    }
}

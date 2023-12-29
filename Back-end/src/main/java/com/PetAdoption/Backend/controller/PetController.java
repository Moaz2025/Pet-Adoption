package com.PetAdoption.Backend.controller;

import com.PetAdoption.Backend.entity.Pet;
import com.PetAdoption.Backend.entity.PetResponse;
import com.PetAdoption.Backend.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Pet")
@CrossOrigin(origins = "http://localhost:3000")
public class PetController {
    @Autowired
    PetService petService;


    @GetMapping("/getAllPets")
    public ResponseEntity<List<PetResponse>> getAllPets(){
        return new ResponseEntity<>(petService.convertToPetResponseList(petService.findAllPets()), HttpStatus.ACCEPTED);
    }
    @Transactional
    @GetMapping("/getPetById")
    public ResponseEntity<PetResponse> getPetById(@RequestParam(name="id") Integer id){
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

        return new ResponseEntity<>(petResponse,HttpStatus.ACCEPTED);
    }



    @GetMapping("/getPetByName")
    public ResponseEntity<List<PetResponse>> getPetByName(@RequestParam(name="name") String name){
        return new ResponseEntity<>(petService.convertToPetResponseList(petService.findPetByName(name)),HttpStatus.ACCEPTED);
    }

    @GetMapping("/getPetByBehavior")
    public ResponseEntity<List<PetResponse>> getPetByBehavior(@RequestParam(name="behavior") String behavior){
        return new ResponseEntity<List<PetResponse>>(petService.convertToPetResponseList(petService.findByBehavior(behavior)), HttpStatus.ACCEPTED);
    }
    @GetMapping("/getPetByBreed")
    public ResponseEntity<List<PetResponse>> getPetByBreed(@RequestParam(name="breed") String breed){
        return new ResponseEntity<List<PetResponse>>(petService.convertToPetResponseList(petService.findByBreed(breed)),HttpStatus.ACCEPTED);
    }
    @GetMapping("/getPetByGender")
    public ResponseEntity<List<PetResponse>> getPetByGender(@RequestParam(name="gender") String gender){
        return new ResponseEntity<List<PetResponse>>(petService.convertToPetResponseList(petService.findByGender(gender)),HttpStatus.ACCEPTED);
    }
    @GetMapping("/getPetByHouseTraining")
    public ResponseEntity<List<PetResponse>> getPetByHouseTraining(@RequestParam(name="houseTraining") Boolean houseTraining){
        return new ResponseEntity<List<PetResponse>>(petService.convertToPetResponseList(petService.findByHouseTraining(houseTraining)),HttpStatus.ACCEPTED);
    }
    @GetMapping("/getPetByNeutering")
    public ResponseEntity<List<PetResponse>> getPetByNeutering(@RequestParam(name="neutering") String neutering){
        return new ResponseEntity<List<PetResponse>>(petService.convertToPetResponseList(petService.findByNeutering(neutering)),HttpStatus.ACCEPTED);
    }
    @GetMapping("/getPetBySpaying")
    public ResponseEntity<List<PetResponse>> getPetBySpaying(@RequestParam(name="spaying") String spaying){
        return new ResponseEntity<List<PetResponse>>(petService.convertToPetResponseList(petService.findBySpaying(spaying)),HttpStatus.ACCEPTED);
    }
    @GetMapping("/getPetBySpecies")
    public ResponseEntity<List<PetResponse>> getPetBySpecies(@RequestParam(name="species") String species){
        return new ResponseEntity<List<PetResponse>>(petService.convertToPetResponseList(petService.findBySpecies(species)),HttpStatus.ACCEPTED);
    }

    @GetMapping("/getPetByVaccination")
    public ResponseEntity<List<PetResponse>> getPetByVaccination(@RequestParam(name="vaccination") String vaccination){
        return new ResponseEntity<List<PetResponse>>(petService.convertToPetResponseList(petService.findByVaccination(vaccination)),HttpStatus.ACCEPTED);
    }
    @GetMapping("/getPetByShelter")
    public ResponseEntity<List<PetResponse>> getPetByShelter(@RequestParam(name="shelter") String shelter){
        return new ResponseEntity<List<PetResponse>>(petService.convertToPetResponseList(petService.findByShelterName(shelter)),HttpStatus.ACCEPTED);
    }
    @GetMapping("/getPetByAge")
    public ResponseEntity<List<PetResponse>> getPetByAge(@RequestParam(name="age") Float age){
        return new ResponseEntity<List<PetResponse>>(petService.convertToPetResponseList(petService.findByAgeLessThan(age)),HttpStatus.ACCEPTED);
    }
}

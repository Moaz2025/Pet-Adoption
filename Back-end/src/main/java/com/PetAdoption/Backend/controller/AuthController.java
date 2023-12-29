package com.PetAdoption.Backend.controller;

import com.PetAdoption.Backend.entity.Adopter;
import com.PetAdoption.Backend.service.AdopterService;
import com.PetAdoption.Backend.service.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {
    Validation validation = new Validation();
    @Autowired
    private AdopterService adopterService;
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Adopter adopter){
        Adopter dataAdopter = adopterService.getAdopterByEmail(adopter.getEmail());
        if(!validation.validateEmail(adopter.getEmail())){
            return new ResponseEntity<>("Email is not valid", HttpStatus.BAD_REQUEST);
        }
        if (dataAdopter == null){
            adopter.setSalt(validation.getSalt());
            adopter.setPassword(validation.hashPassword(adopter.getPassword(),adopter.getSalt()));
            adopterService.createAdopter(adopter);
            return new ResponseEntity<>("Signed up successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Email is already registered", HttpStatus.BAD_REQUEST);
    }
}
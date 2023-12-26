package com.PetAdoption.Backend.controller;

import com.PetAdoption.Backend.entity.*;
import com.PetAdoption.Backend.service.ManagerService;
import com.PetAdoption.Backend.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shelter")
@CrossOrigin(origins = "http://localhost:3000")
public class ShelterController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private ShelterService shelterService;

    @PutMapping("/manageShelter")
    public ResponseEntity<ShelterResponse> editShelter(@RequestHeader("Authorization") String token, @RequestBody Shelter shelter) {
        token = token.replace("Bearer ", "");
        ShelterResponse shelterResponse = new ShelterResponse();
        if(managerService.getManagerByToken(token) == null){
            shelterResponse.setMessage("Not authorized manager");
            return new ResponseEntity<>(shelterResponse, HttpStatus.FORBIDDEN);
        }
        Manager manager = managerService.getManagerByToken(token);
        String shelterName = manager.getShelter().getName();
        Shelter dataShelter = shelterService.getShelterByName(shelterName);
        dataShelter.setLocation(shelter.getLocation());
        dataShelter.setPhone(shelter.getPhone());
        shelterService.updateShelter(dataShelter);
        shelterResponse.setMessage("Shelter updated successfully");
        shelterResponse.setShelter(dataShelter);
        return new ResponseEntity<>(shelterResponse, HttpStatus.ACCEPTED);
    }
}

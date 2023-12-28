package com.PetAdoption.Backend.controller;



import com.PetAdoption.Backend.entity.AdoptionApplicationDTO;

import com.PetAdoption.Backend.service.AdoptionApplicationService;
import com.PetAdoption.Backend.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/application")
public class AdoptionApplicationController {
    @Autowired
    AdoptionApplicationService appService;
    @Autowired
    StaffService     ss;
    @GetMapping("/list")
    List<AdoptionApplicationDTO> getAllRequests(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        return appService.getAllRequests(token);
    }


    @PutMapping("/accept/{id}")
    ResponseEntity<String> acceptRequest(@PathVariable(name="id") int id ,@RequestHeader(HttpHeaders.AUTHORIZATION) String token ){
        if (ss.existsByToken(token))
        {
            if(appService.acceptApp(id)){
                return ResponseEntity.status(200).body("Done");
            }
            else{
                return ResponseEntity.status(409).body("This Pet is booked");

            }
        }
        else
        {
            return ResponseEntity.status(409).body("Not Authorized");
        }

    }

    //refuseRequest
    @PutMapping("/refuse/{id}")
    ResponseEntity<String> refuseRequest(@PathVariable(name="id") int id ,@RequestHeader(HttpHeaders.AUTHORIZATION) String token ){
        if (ss.existsByToken(token))
        {
            if( appService.refuseApp(id))
                return ResponseEntity.status(200).body("Done");
            else
                return ResponseEntity.status(409).body("the app have been accepted before");




        }
        else
        {
            return ResponseEntity.status(409).body("Not Authorized");

        }


    }
}

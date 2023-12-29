package com.PetAdoption.Backend.controller;



import com.PetAdoption.Backend.entity.Adopter;
import com.PetAdoption.Backend.entity.AdoptionApplication;
import com.PetAdoption.Backend.entity.AdoptionApplicationDTO;

import com.PetAdoption.Backend.entity.ApplicationResponse;
import com.PetAdoption.Backend.repository.AdopterRepository;
import com.PetAdoption.Backend.service.AdoptionApplicationService;
import com.PetAdoption.Backend.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/application")
public class AdoptionApplicationController {
    @Autowired
    AdoptionApplicationService appService;
    @Autowired
    StaffService     ss;
    @Autowired
    AdoptionApplicationService adoptionApplicationService;
    @Autowired
    AdopterRepository adopterRepository;
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
    @GetMapping("/getAllMyApplication")
    public ResponseEntity<List<ApplicationResponse>> getAllMyApplication(@RequestHeader("Authorization") String token){
        token = token.replace("Bearer ", "");
        Adopter adopter = adopterRepository.findByToken(token);
        if(adopter == null){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        List<AdoptionApplication> adoptionApplicationList  = adoptionApplicationService.getAllMyApplications(adopter);
        List<ApplicationResponse> applicationResponseList = new ArrayList<>();
        int i = 0;
        for(AdoptionApplication app : adoptionApplicationList){
            ApplicationResponse applicationResponse = new ApplicationResponse();
            applicationResponse.setAdopterEmail(adoptionApplicationList.get(i).getAdopter().getEmail());
            applicationResponse.setPetId(adoptionApplicationList.get(i).getPet().getId());
            applicationResponse.setStatus((adoptionApplicationList.get(i).getStatus()));
            applicationResponseList.add(applicationResponse);
            i++;
        }
        if(applicationResponseList.size() < 1) return new ResponseEntity<>(null,HttpStatus.ACCEPTED);
        return new ResponseEntity<>(applicationResponseList,HttpStatus.ACCEPTED);
    }
    // assume any one can apply request
    @PostMapping("/requestAdoption")
    public String requestAdoption(@RequestBody ApplicationResponse applicationResponse){
        return adoptionApplicationService.requestAdoption(applicationResponse);
    }
}

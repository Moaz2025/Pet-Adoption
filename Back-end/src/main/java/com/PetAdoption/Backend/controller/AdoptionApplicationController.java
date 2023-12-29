package com.PetAdoption.Backend.controller;

import com.PetAdoption.Backend.entity.Adopter;
import com.PetAdoption.Backend.entity.AdopterResponse;
import com.PetAdoption.Backend.entity.AdoptionApplication;
import com.PetAdoption.Backend.entity.ApplicationResponse;
import com.PetAdoption.Backend.repository.AdopterRepository;
import com.PetAdoption.Backend.repository.AdoptionApplicationRepository;
import com.PetAdoption.Backend.service.AdminService;
import com.PetAdoption.Backend.service.AdoptionApplicationService;
import com.PetAdoption.Backend.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/app")
@CrossOrigin(origins = "http://localhost:3000")
public class AdoptionApplicationController {
    @Autowired
    AdoptionApplicationService adoptionApplicationService;
    @Autowired
    AdopterRepository adopterRepository;

    //  managers and admins can only retrieve the data need to check the Authorization
    @GetMapping("/getAllMyApplication")
    public ResponseEntity<List<ApplicationResponse>> getAllMyApplication(@RequestHeader("Authorization") String token){
        token = token.replace("Bearer ", "");
        Adopter adopter = adopterRepository.findByToken(token);
        if(adopter == null){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        List<AdoptionApplication> adoptionApplicationList  = adoptionApplicationService.getAllMyApplications(adopter);
        List<ApplicationResponse> applicationResponseList = new ArrayList<>();
        int i = 0;
        for(AdoptionApplication app : adoptionApplicationList){
            ApplicationResponse applicationResponse = new ApplicationResponse();
            applicationResponse.setAdopterEmail(adoptionApplicationList.get(i).getAdopter().getEmail());
            applicationResponse.setPetId(adoptionApplicationList.get(i).getPet().getId());
            applicationResponse.setStatus((adoptionApplicationList.get(i).isStatus()));
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

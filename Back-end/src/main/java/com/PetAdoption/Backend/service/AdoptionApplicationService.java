package com.PetAdoption.Backend.service;

import com.PetAdoption.Backend.entity.Adopter;
import com.PetAdoption.Backend.entity.AdoptionApplication;
import com.PetAdoption.Backend.entity.ApplicationResponse;
import com.PetAdoption.Backend.entity.Pet;
import com.PetAdoption.Backend.repository.AdopterRepository;
import com.PetAdoption.Backend.repository.AdoptionApplicationRepository;
import com.PetAdoption.Backend.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionApplicationService {
    @Autowired
    com.PetAdoption.Backend.repository.AdoptionApplicationRepository  adoptionApplicationRepository;
    @Autowired
    AdopterRepository adopterRepository;

    @Autowired
    PetRepository petRepository;

    public String  requestAdoption (ApplicationResponse applicationResponse){

        try {
            AdoptionApplication adoptionApplication = convertFromAppResToAdoption(applicationResponse);
            if(adoptionApplication == null) return "Unsaved";
            adoptionApplicationRepository.save(adoptionApplication);
            return "saved";
        }catch (Exception e){
            System.out.println(e);
            return "can't adopt this pet";
        }
    }

    public List<AdoptionApplication> getAllMyApplications(Adopter adopter){return adoptionApplicationRepository.findAllByAdopter(adopter);}
    private AdoptionApplication convertFromAppResToAdoption(ApplicationResponse applicationResponse){
        AdoptionApplication adoptionApplication = new AdoptionApplication();
        Adopter adopter = adopterRepository.findByEmail(applicationResponse.getAdopterEmail());
        if(adopter == null  )return null;
        adoptionApplication.setAdopter(adopter);
        Pet pet = petRepository.findById(applicationResponse.getPetId()).orElse(null);
        if(pet == null )return null;
        adoptionApplication.setPet(pet);
        adoptionApplication.setStatus(Boolean.parseBoolean(String.valueOf(applicationResponse.isStatus()))); // I won't make restriction on this part as many one can apply for same pet
        return adoptionApplication;
    }

}

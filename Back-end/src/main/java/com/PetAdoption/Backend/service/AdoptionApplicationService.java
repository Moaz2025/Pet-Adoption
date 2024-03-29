package com.PetAdoption.Backend.service;

import com.PetAdoption.Backend.entity.*;
import com.PetAdoption.Backend.repository.AdopterRepository;
import com.PetAdoption.Backend.repository.AdoptionApplicationRepository;
import com.PetAdoption.Backend.repository.NotificationRepository;
import com.PetAdoption.Backend.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class AdoptionApplicationService {
    @Autowired
    AdoptionApplicationRepository ar;
    @Autowired
    StaffService ss;
    @Autowired
    PetRepository pr;
    @Autowired
    NotificationRepository nr;
    @Autowired
    AdopterRepository adopterRepository;
    @Autowired
    AdoptionApplicationRepository  adoptionApplicationRepository;
    @Autowired
    PetRepository petRepository;

    public String  requestAdoption (ApplicationResponse applicationResponse){

        try {
            AdoptionApplication adoptionApplication = convertFromAppResToAdoption(applicationResponse);
            List<AdoptionApplication> adoptionApplicationList = adoptionApplicationRepository.findByPetAndAdopter(adoptionApplication.getPet(),adoptionApplication.getAdopter());
            // boolean isSameAdoptionApplication = false;
            for(AdoptionApplication adoptionApplication1 : adoptionApplicationList){
                if(adoptionApplication1.getPet().getId() == adoptionApplication.getPet().getId() &&
                        adoptionApplication1.getAdopter().getEmail().equals(adoptionApplication.getAdopter().getEmail())) {
                        if (!adoptionApplication1.getStatus().equals("Refused") )
                            return "you have applied before";
                }
            }

            if(adoptionApplication == null) return "can't adopt this";
            adoptionApplicationRepository.save(adoptionApplication);
            return "you request is pending now";
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
        adoptionApplication.setStatus(applicationResponse.getStatus()); // I won't make restriction on this part as many one can apply for same pet
        return adoptionApplication;
    }

    public List<AdoptionApplicationDTO> getAllRequests(String token){
        Staff staff= ss.getStaffByToken(token);
        if(staff==null){
            return null;

        }
        else{
            List<AdoptionApplicationDTO> result = new ArrayList<>();
            String sheltername =  staff.getShelter().getName();
            List<Pet>   listofpets = pr.findByShelter_Name(sheltername);
            for (Pet pet: listofpets){
                List<AdoptionApplication> petapps =ar.findByPet_Id(pet.getId());
                for(AdoptionApplication app : petapps){
                    AdoptionApplicationDTO x = new AdoptionApplicationDTO();
                    x.setStatus(app.getStatus());
                    x.setAdopterEmail(app.getAdopter().getEmail());
                    x.setPetId(app.getPet().getId());
                    x.setAppId(app.getId());
                    result.add(x);

                }


            }
            return result;
        }
    }
    public boolean acceptApp(int id) {
        AdoptionApplication app = ar.findById(id);
        Pet pet = app.getPet();
        if (pet.isBooked() || app.getStatus().equalsIgnoreCase("Refused"))
            return false;
        pet.setBooked(true);
        app.setPet(pet);
        app.setStatus("Accepted");
        // this part is replaced with a trigger
        ar.save(app);
        /*Notification notification = new Notification();
        notification.setPet(pet);
        notification.setAdopter(app.getAdopter());
        notification.setContent("your Adoption Application for Pet of id "+pet.getId()+" has been accepted");
        nr.save(notification);*/
        return true;
    }
    public boolean refuseApp(int id ){
        AdoptionApplication app = ar.findById(id);
        Pet pet = app.getPet();
        if(app.getStatus().equals("Accepted"))
            return false;
        else{
            app.setStatus("Refused");
            // this part is replaced with trigger
            ar.save(app);
            /*Notification notification = new Notification();
            notification.setPet(pet);
            notification.setAdopter(app.getAdopter());
            notification.setContent("your Adoption Application for Pet of id "+pet.getId()+" has been refused");
            nr.save(notification);*/
            return true;}
    }

}

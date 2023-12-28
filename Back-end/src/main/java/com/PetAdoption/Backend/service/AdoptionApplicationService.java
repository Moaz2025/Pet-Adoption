package com.PetAdoption.Backend.service;

import com.PetAdoption.Backend.entity.AdoptionApplication;
import com.PetAdoption.Backend.entity.AdoptionApplicationDTO;
import com.PetAdoption.Backend.entity.Staff;
import com.PetAdoption.Backend.entity.Pet;
import com.PetAdoption.Backend.repository.AdoptionApplicationRepository;
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
                 result.add(x);

             }


             }
             return result;


         }


     }
     public boolean acceptApp(int id ){
        AdoptionApplication app = ar.findById(id);

         Pet pet = app.getPet();
        if (pet.isBooked()){
            return false;
        }
        else{
            pet.setBooked(true);
            app.setStatus("Accepted");
            ar.save(app);
            return true;




        }



     }
    public boolean refuseApp(int id ){
        AdoptionApplication app = ar.findById(id);
        if(app.getStatus().equals("Accepted"))
            return false;
        else{
        app.setStatus("Refused");

        ar.save(app);
        return true;}



    }

}

package com.PetAdoption.Backend.service;

import com.PetAdoption.Backend.entity.*;
import com.PetAdoption.Backend.repository.AttachmentRepository;
import com.PetAdoption.Backend.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    PetRepository pr;
    @Autowired
    ShelterService ss;
    @Autowired
    AttachmentRepository ar;

    public void addPetProfile(petAddFormDTO form, Shelter shelter){
        Pet pet =new Pet();

        pet.setBooked(false);
        pet.setAge(form.getAge());
        pet.setBreed(form.getBreed());
        pet.setGender(form.getGender());
        pet.setBehavior(form.getBehavior());
        pet.setDescription(form.getDescription());
        pet.setName(form.getName());
        pet.setHouseTraining(form.isHouseTraining());
        pet.setNeutering(form.getNeutering());
        pet.setSpecies(form.getSpecies());
        pet.setVaccination(form.getVaccination());
        pet.setSpaying(form.getSpaying());
        pet.setShelter(shelter);
        pr.save(pet);





    }
    public boolean editPetProfile(petAddFormDTO form, int id){
       Optional<Pet> pet = pr.findById(id);
       if(pet.get().isBooked()) {
           return false;

       }


        pet.get().setAge(form.getAge());
        pet.get().setBreed(form.getBreed());
        pet.get().setGender(form.getGender());
        pet.get().setBehavior(form.getBehavior());
        pet.get().setDescription(form.getDescription());
        pet.get().setName(form.getName());
        pet.get().setHouseTraining(form.isHouseTraining());
        pet.get().setNeutering(form.getNeutering());
        pet.get().setSpecies(form.getSpecies());
        pet.get().setVaccination(form.getVaccination());
        pet.get().setSpaying(form.getSpaying());
        pr.save(pet.get());
        return true;





    }
    public void addAttachment(AttachmentDTO attachmentDTO){
        Attachment attachmentobj = new Attachment();
        attachmentobj.setPet(pr.findById(attachmentDTO.getPetId()).get());
        attachmentobj.setAttachment(attachmentDTO.getAttachment());
        ar.save(attachmentobj);



    }

    public Optional<Pet> getPetById (int id){
        return pr.findById(id);
    }
  public List<AttachmentDTO> getAllAttachments(int petId){
      List<Attachment> list1 = ar.findByPet_Id(petId);
      List<AttachmentDTO> list2 = new ArrayList<>();
      for(Attachment entry : list1){
          AttachmentDTO obj = new AttachmentDTO();
          obj.setPetId(petId);
          obj.setAttachment(entry.getAttachment());
          list2.add(obj);

      }
      return list2;
  }


}

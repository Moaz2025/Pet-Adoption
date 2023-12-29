package com.PetAdoption.Backend.controller;
import com.PetAdoption.Backend.entity.AttachmentDTO;
import com.PetAdoption.Backend.entity.Pet;
import com.PetAdoption.Backend.entity.PetResponse;
import com.PetAdoption.Backend.entity.petAddFormDTO;
import com.PetAdoption.Backend.service.PetService;
import com.PetAdoption.Backend.service.StaffService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pet")
@CrossOrigin(origins = "http://localhost:3000")
public class PetController {
    @Autowired
    PetService ps;
    @Autowired
    StaffService ss;

    @PostMapping("/add")
    public ResponseEntity<String> addPetProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody petAddFormDTO form  ){
        token = token.replace("Bearer ", "");
        if (!ss.existsByToken(token))
            return ResponseEntity.status(409).body("Not Authorized");
        ps.addPetProfile(form,ss.getStaffByToken(token).getShelter());
        return  ResponseEntity.status(200).body("pet is added to your shelter");

    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editPetProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody petAddFormDTO form , @PathVariable(name="id") int id  ){
        token = token.replace("Bearer ", "");
        if(!ss.existsByToken(token))
            return ResponseEntity.status(409).body("Not Authorized");
        if( ps.editPetProfile(form,id))
            return  ResponseEntity.status(200).body("pet profile is edited");
        else
            return  ResponseEntity.status(409).body("the pet is booked so its profile cannot be edited");


    }

    @GetMapping("/getAllPets")
    public List<PetResponse> getAllPets(){
        return ps.convertToPetResponseList(ps.findAllPets());
    }
    @Transactional
    @GetMapping("/getPetById")
    public PetResponse getPetById(@RequestParam(name="id") Integer id){
        if(id == null)return null;
        PetResponse petResponse;
        Pet pet;
        try {
            // use pet
            pet = ps.findPetProfileById(id);
//            System.out.println(pet.getId());
            if(pet == null) return null;
            petResponse = ps.convertToPetResponse(pet);
        }catch (EntityNotFoundException e){
            System.out.println(e);
            return null;
        }

        return petResponse;
    }

    @PostMapping("/attach")
    public ResponseEntity<String> addAttachment(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody AttachmentDTO attachment) {
        token = token.replace("Bearer ", "");
        if (!ss.existsByToken(token)) {
            return ResponseEntity.status(409).body("Not Authorized");
        }
        Optional<Pet> pet = ps.getPetById(attachment.getPetId());
        if (!pet.isPresent()) {
            return ResponseEntity.status(409).body("Pet is not found");
        }

        if (ss.getStaffByToken(token).getShelter().getName() != pet.get().getShelter().getName()) {
            return ResponseEntity.status(409).body("Not Authorized to add attachment to a pet not in your shelter");
        }
        ps.addAttachment(attachment);
        return ResponseEntity.status(200).body("Attachment Added!");


    }

    @GetMapping("/getAllAttachments/{PetId}")
    public List<AttachmentDTO> getAllAttachments(@PathVariable(name = "PetId") int petId) {
        return ps.getAllAttachments(petId);

    }

}

package com.PetAdoption.Backend.controller;

import com.PetAdoption.Backend.entity.*;
import com.PetAdoption.Backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/promotion")
@CrossOrigin(origins = "http://localhost:3000")
public class PromotionController {

    @Autowired
    private AdopterService adopterService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ShelterService shelterService;

    @GetMapping("/getAllAdopters")
    public ResponseEntity<AdopterResponse> getAllAdopters(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        AdopterResponse adopterResponse = new AdopterResponse();
        if(managerService.getManagerByToken(token) == null && adminService.getAdminByToken(token) == null){
            adopterResponse.setMessage("Not authorized");
            return new ResponseEntity<>(adopterResponse, HttpStatus.FORBIDDEN);
        }
        adopterResponse.setMessage("List of adopters");
        List<Adopter> dataAdopter;
        dataAdopter = adopterService.getAllAdopters();
        List<AdopterAttributes> adopterAttributes = new ArrayList<>();
        for (Adopter adopter : dataAdopter) {
            String email = adopter.getEmail();
            String name = adopter.getName();
            AdopterAttributes attributes = new AdopterAttributes();
            attributes.setEmail(email);
            attributes.setName(name);
            adopterAttributes.add(attributes);
        }
        adopterResponse.setAdoptersList(adopterAttributes);
        return new ResponseEntity<>(adopterResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/promoteToStaff")
    public ResponseEntity<String> promoteToStaff(@RequestHeader("Authorization") String token, @RequestBody StaffData staffData){
        token = token.replace("Bearer ", "");
        if(managerService.getManagerByToken(token) == null){
            return new ResponseEntity<>("Not authorized manager", HttpStatus.FORBIDDEN);
        }
        String email = staffData.getEmail();
        if(adopterService.getAdopterByEmail(email) == null){
            return new ResponseEntity<>("No adopter with this email", HttpStatus.NOT_FOUND);
        }
        Adopter adopter = adopterService.getAdopterByEmail(email);
        int atIndex = email.indexOf('@');
        String prefix = email.substring(0, atIndex);
        String suffix = email.substring(atIndex);
        String staffModifiedEmail = prefix + "_staff" + suffix;
        if(staffService.getStaffByEmail(staffModifiedEmail) != null){
            return new ResponseEntity<>("Already promoted as staff", HttpStatus.CONFLICT);
        }
        String managerModifiedEmail = prefix + "_manager" + suffix;
        if(managerService.getManagerByEmail(managerModifiedEmail) != null){
            return new ResponseEntity<>("Already promoted as manager", HttpStatus.CONFLICT);
        }
        Manager manager = managerService.getManagerByToken(token);
        Shelter shelter = manager.getShelter();
        Staff staff = new Staff();
        staff.setEmail(staffModifiedEmail);
        staff.setName(adopter.getName());
        staff.setSalt(adopter.getSalt());
        staff.setPassword(adopter.getPassword());
        staff.setPhone(adopter.getPhone());
        staff.setRole(staffData.getRole());
        staff.setShelter(shelter);
        staffService.createStaff(staff);
        return new ResponseEntity<>("Adopter promoted successfully", HttpStatus.ACCEPTED);
    }

    @PostMapping("/promoteToManager")
    public ResponseEntity<String> promoteToManager(@RequestHeader("Authorization") String token, @RequestBody ManagerData managerData){
        token = token.replace("Bearer ", "");
        if(adminService.getAdminByToken(token) == null){
            return new ResponseEntity<>("Not authorized admin", HttpStatus.FORBIDDEN);
        }
        String email = managerData.getEmail();
        if(adopterService.getAdopterByEmail(email) == null){
            return new ResponseEntity<>("No adopter with this email", HttpStatus.NOT_FOUND);
        }
        Adopter adopter = adopterService.getAdopterByEmail(email);
        int atIndex = email.indexOf('@');
        String prefix = email.substring(0, atIndex);
        String suffix = email.substring(atIndex);
        String staffModifiedEmail = prefix + "_staff" + suffix;
        if(staffService.getStaffByEmail(staffModifiedEmail) != null){
            return new ResponseEntity<>("Already promoted as staff", HttpStatus.CONFLICT);
        }
        String managerModifiedEmail = prefix + "_manager" + suffix;
        if(managerService.getManagerByEmail(managerModifiedEmail) != null){
            return new ResponseEntity<>("Already promoted as manager", HttpStatus.CONFLICT);
        }
        Shelter shelter = new Shelter();
        shelter.setName(managerData.getShelterName());
        shelter.setLocation(managerData.getShelterLocation());
        shelter.setPhone(managerData.getShelterPhone());
        shelterService.createShelter(shelter);
        Manager manager = new Manager();
        manager.setEmail(managerModifiedEmail);
        manager.setName(adopter.getName());
        manager.setSalt(adopter.getSalt());
        manager.setPassword(adopter.getPassword());
        manager.setPhone(adopter.getPhone());
        manager.setShelter(shelter);
        managerService.createManager(manager);
        return new ResponseEntity<>("Adopter promoted successfully", HttpStatus.ACCEPTED);
    }
}

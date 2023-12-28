package com.PetAdoption.Backend.controller;

import com.PetAdoption.Backend.entity.*;
import com.PetAdoption.Backend.service.AdminService;
import com.PetAdoption.Backend.service.AdopterService;
import com.PetAdoption.Backend.service.ManagerService;
import com.PetAdoption.Backend.service.StaffService;
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
    public ResponseEntity<String> promoteToStaff(@RequestHeader("Authorization") String token, @RequestBody String email){
        token = token.replace("Bearer ", "");
        if(managerService.getManagerByToken(token) == null){
            return new ResponseEntity<>("Not authorized manager", HttpStatus.FORBIDDEN);
        }
        if(adopterService.getAdopterByEmail(email) == null){
            return new ResponseEntity<>("No adopter with this email", HttpStatus.NOT_FOUND);
        }
        Adopter adopter = adopterService.getAdopterByEmail(email);
        int atIndex = email.indexOf('@');
        String prefix = email.substring(0, atIndex);
        String suffix = email.substring(atIndex);
        String modifiedEmail = prefix + "_staff" + suffix;
        if(staffService.getStaffByEmail(modifiedEmail) != null){
            return new ResponseEntity<>("Already promoted", HttpStatus.CONFLICT);
        }
        Staff staff = new Staff();
        staff.setEmail(modifiedEmail);
        staff.setName(adopter.getName());
        staff.setSalt(adopter.getSalt());
        staff.setPassword(adopter.getPassword());
        staff.setPhone(adopter.getPhone());
        staffService.createStaff(staff);
        return new ResponseEntity<>("Adopter promoted successfully", HttpStatus.ACCEPTED);
    }

    @PostMapping("/promoteToManager")
    public ResponseEntity<String> promoteToManager(@RequestHeader("Authorization") String token, @RequestBody String email){
        token = token.replace("Bearer ", "");
        if(adminService.getAdminByToken(token) == null){
            return new ResponseEntity<>("Not authorized admin", HttpStatus.FORBIDDEN);
        }
        if(adopterService.getAdopterByEmail(email) == null){
            return new ResponseEntity<>("No adopter with this email", HttpStatus.NOT_FOUND);
        }
        Adopter adopter = adopterService.getAdopterByEmail(email);
        int atIndex = email.indexOf('@');
        String prefix = email.substring(0, atIndex);
        String suffix = email.substring(atIndex);
        String modifiedEmail = prefix + "_manager" + suffix;
        if(adminService.getAdminByEmail(modifiedEmail) != null){
            return new ResponseEntity<>("Already promoted", HttpStatus.CONFLICT);
        }
        Manager manager = new Manager();
        manager.setEmail(modifiedEmail);
        manager.setName(adopter.getName());
        manager.setSalt(adopter.getSalt());
        manager.setPassword(adopter.getPassword());
        manager.setPhone(adopter.getPhone());
        managerService.createManager(manager);
        return new ResponseEntity<>("Adopter promoted successfully", HttpStatus.ACCEPTED);
    }
}

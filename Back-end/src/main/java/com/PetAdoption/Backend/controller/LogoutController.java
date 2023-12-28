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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class LogoutController {
    @Autowired
    private AdopterService adopterService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private AdminService adminService;
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LoginResponse loginResponse){
        if(loginResponse.getUserType().equalsIgnoreCase("adopter")) {
            Adopter adopter = adopterService.getAdopterByEmail(loginResponse.getEmail());
            adopter.setToken(null);
            adopterService.updateAdopter(adopter);
        }
        if(loginResponse.getUserType().equalsIgnoreCase("staff")){
            Staff staff = staffService.getStaffByEmail(loginResponse.getEmail());
            staff.setToken(null);
            staffService.updateStaff(staff);
        }
        if(loginResponse.getUserType().equalsIgnoreCase("manager")) {
            Manager manager = managerService.getManagerByEmail(loginResponse.getEmail());
            manager.setToken(null);
            managerService.updateManager(manager);
        }
        if(loginResponse.getUserType().equalsIgnoreCase("admin")){
            Admin admin = adminService.getAdminByEmail(loginResponse.getEmail());
            admin.setToken(null);
            adminService.updateAdmin(admin);
        }
        return new ResponseEntity<>("Logged out successfully ", HttpStatus.ACCEPTED);
    }
}


package com.PetAdoption.Backend.controller;

import com.PetAdoption.Backend.entity.*;
import com.PetAdoption.Backend.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class LoginController {
    Validation validation = new Validation();
    @Autowired
    private AdopterService adopterService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private AdminService adminService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginForm loginForm){
        LoginResponse loginResponse = new LoginResponse();

        if(loginForm.getUserType().equalsIgnoreCase("adopter")){
            Adopter adopter = adopterService.getAdopterByEmail(loginForm.getEmail());
            if (adopter == null){
                loginResponse.setMessage("User does not exist");
                return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
            }
            if(validation.verifyPassword(loginForm.getPassword(), adopter.getPassword(), adopter.getSalt())){
                loginResponse.setMessage("Logged in successfully");
                loginResponse.setUserType("adopter");
                loginResponse.setEmail(loginForm.getEmail());
                Token token = new Token();
                String generatedToken = token.generateToken();
                adopter.setToken(generatedToken);
                loginResponse.setToken(generatedToken);
                adopterService.updateAdopter(adopter);
                return new ResponseEntity<>(loginResponse, HttpStatus.ACCEPTED);
            }
            loginResponse.setMessage("Wrong password");
        }

        if(loginForm.getUserType().equalsIgnoreCase("staff")){
            Staff staff = staffService.getStaffByEmail(loginForm.getEmail());
            if (staff == null){
                loginResponse.setMessage("User does not exist");
                return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
            }
            if(validation.verifyPassword(loginForm.getPassword(),staff.getPassword(),staff.getSalt())){
                loginResponse.setMessage("Logged in successfully");
                loginResponse.setUserType("staff");
                loginResponse.setEmail(loginForm.getEmail());
                Token token = new Token();
                String generatedToken = token.generateToken();
                staff.setToken(generatedToken);
                loginResponse.setToken(generatedToken);
                staffService.updateStaff(staff);
                return new ResponseEntity<>(loginResponse, HttpStatus.ACCEPTED);
            }
            loginResponse.setMessage("Wrong password");
        }

        if(loginForm.getUserType().equalsIgnoreCase("manager")){
            Manager manager = managerService.getManagerByEmail(loginForm.getEmail());
            if (manager == null){
                loginResponse.setMessage("User does not exist");
                return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
            }
            if(validation.verifyPassword(loginForm.getPassword(),manager.getPassword(),manager.getSalt())){
                loginResponse.setMessage("Logged in successfully");
                loginResponse.setUserType("manager");
                loginResponse.setEmail(loginForm.getEmail());
                Token token = new Token();
                String generatedToken = token.generateToken();
                manager.setToken(generatedToken);
                loginResponse.setToken(generatedToken);
                managerService.updateManager(manager);
                return new ResponseEntity<>(loginResponse, HttpStatus.ACCEPTED);
            }
            loginResponse.setMessage("Wrong password");
        }

        if(loginForm.getUserType().equalsIgnoreCase("admin")){
            Admin hardCodedAdmin = new Admin();
            Validation validation = new Validation();
            hardCodedAdmin.setEmail("admin@petadoption.com");
            hardCodedAdmin.setSalt(validation.getSalt());
            hardCodedAdmin.setPassword(validation.hashPassword("admin",hardCodedAdmin.getSalt()));
            hardCodedAdmin.setName("Hardcoded Admin");
            adminService.createAdmin(hardCodedAdmin);
            Admin admin = adminService.getAdminByEmail(loginForm.getEmail());
            if (admin == null){
                loginResponse.setMessage("User does not exist");
                return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
            }
            if(validation.verifyPassword(loginForm.getPassword(),admin.getPassword(),admin.getSalt())){
                loginResponse.setMessage("Logged in successfully");
                loginResponse.setUserType("admin");
                loginResponse.setEmail(loginForm.getEmail());
                Token token = new Token();
                String generatedToken = token.generateToken();
                admin.setToken(generatedToken);
                loginResponse.setToken(generatedToken);
                adminService.updateAdmin(admin);
                return new ResponseEntity<>(loginResponse, HttpStatus.ACCEPTED);
            }
            loginResponse.setMessage("Wrong password");
        }
        return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
    }
}
package com.PetAdoption.Backend.service;

import com.PetAdoption.Backend.entity.Admin;
import com.PetAdoption.Backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin getAdminByEmail(String email){
        return adminRepository.findById(email).orElse(null);
    }

    public Admin getAdminByToken(String token){
        return adminRepository.findByToken(token);
    }

    public Admin updateAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

}
package com.PetAdoption.Backend.service;

import com.PetAdoption.Backend.entity.Manager;
import com.PetAdoption.Backend.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public Manager createManager(Manager manager) {
        return managerRepository.save(manager);
    }

    public Manager getManagerByEmail(String email){
        return managerRepository.findById(email).orElse(null);
    }

    public Manager getManagerByToken(String token){
        return managerRepository.findByToken(token);
    }

    public Manager updateManager(Manager manager) {
        return managerRepository.save(manager);
    }

    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public void deleteManager(Manager manager) {
        managerRepository.delete(manager);
    }

}
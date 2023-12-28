package com.PetAdoption.Backend.service;

import com.PetAdoption.Backend.entity.Adopter;
import com.PetAdoption.Backend.repository.AdopterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdopterService {

    @Autowired
    private AdopterRepository adopterRepository;

    public Adopter createAdopter(Adopter adopter) {
        return adopterRepository.save(adopter);
    }

    public Adopter getAdopterByEmail(String email){
        return adopterRepository.findById(email).orElse(null);
    }

    public Adopter getAdopterByToken(String token){
        return adopterRepository.findByToken(token);
    }

    public Adopter updateAdopter(Adopter adopter) {
        return adopterRepository.save(adopter);
    }

    public List<Adopter> getAllAdopters() {
        return adopterRepository.findAll();
    }

    public void deleteAdopter(Adopter adopter) {
        adopterRepository.delete(adopter);
    }
}

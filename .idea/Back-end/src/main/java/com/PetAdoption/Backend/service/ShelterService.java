package com.PetAdoption.Backend.service;

import com.PetAdoption.Backend.entity.Shelter;
import com.PetAdoption.Backend.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;

    public Shelter createShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    public Shelter getShelterByName(String name){
        return shelterRepository.findById(name).orElse(null);
    }

    public Shelter updateShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    public void deleteShelter(Shelter shelter) {
        shelterRepository.delete(shelter);
    }
}

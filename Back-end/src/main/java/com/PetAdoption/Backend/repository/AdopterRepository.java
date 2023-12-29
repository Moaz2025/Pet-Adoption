package com.PetAdoption.Backend.repository;

import com.PetAdoption.Backend.entity.Adopter;
import com.PetAdoption.Backend.entity.AdoptionApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdopterRepository extends JpaRepository<Adopter, String> {
    boolean existsByToken(String token);
    Adopter findByToken(String token);
}

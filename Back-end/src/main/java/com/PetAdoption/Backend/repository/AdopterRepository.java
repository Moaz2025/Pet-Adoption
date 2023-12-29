package com.PetAdoption.Backend.repository;

import com.PetAdoption.Backend.entity.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdopterRepository extends JpaRepository<Adopter, String> {
    Adopter findByToken(String token);
    Adopter findByEmail(String email);
}

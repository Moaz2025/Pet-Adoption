package com.PetAdoption.Backend.repository;

import com.PetAdoption.Backend.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, String> {
}

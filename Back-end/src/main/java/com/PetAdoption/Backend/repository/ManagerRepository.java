package com.PetAdoption.Backend.repository;

import com.PetAdoption.Backend.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, String> {
    Manager findByToken(String token);
}

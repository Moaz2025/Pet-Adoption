package com.PetAdoption.Backend.repository;

import com.PetAdoption.Backend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByToken(String token);
}

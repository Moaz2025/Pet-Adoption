package com.PetAdoption.Backend.repository;

import com.PetAdoption.Backend.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {
    Staff findByToken(String token);
}

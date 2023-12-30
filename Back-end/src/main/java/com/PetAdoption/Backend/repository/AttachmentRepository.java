package com.PetAdoption.Backend.repository;

import com.PetAdoption.Backend.entity.Attachment;
import com.PetAdoption.Backend.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    List<Attachment> findByPet_Id(int petId);
}
package com.PetAdoption.Backend.repository;

import com.PetAdoption.Backend.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
   List<Notification> findByAdopter_Token(String token);
}

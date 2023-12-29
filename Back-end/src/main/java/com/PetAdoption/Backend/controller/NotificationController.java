package com.PetAdoption.Backend.controller;

import com.PetAdoption.Backend.entity.Adopter;
import com.PetAdoption.Backend.entity.AdoptionApplication;
import com.PetAdoption.Backend.entity.Notification;
import com.PetAdoption.Backend.repository.AdopterRepository;
import com.PetAdoption.Backend.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    NotificationRepository nr;
    @Autowired
    AdopterRepository ar;
    @GetMapping("/getNotifications")
    public List<String> getNotifications(@RequestHeader(HttpHeaders.AUTHORIZATION) String token ){
        if(ar.existsByToken(token)){
          Adopter adopter =ar.findByToken(token);
            List<Notification> list = nr.findByAdopter_Token(adopter.getToken());
            List<String> list2 = new ArrayList<>();
            for(Notification entity : list) {
                String x = entity.getContent();
                list2.add(x);


            }
            return list2;




        }
        else
            return new ArrayList<>();





    }

}

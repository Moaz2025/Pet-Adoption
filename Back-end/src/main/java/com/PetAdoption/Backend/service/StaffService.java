package com.PetAdoption.Backend.service;

import com.PetAdoption.Backend.entity.Staff;
import com.PetAdoption.Backend.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff getStaffByEmail(String email){
        return staffRepository.findById(email).orElse(null);
    }

    public Staff getStaffByToken(String token){
        return staffRepository.findByToken(token);
    }

    public Staff updateStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public void deleteStaff(Staff staff) {
        staffRepository.delete(staff);
    }

    public boolean existsByToken(String token) { return staffRepository.existsByToken(token);}


}

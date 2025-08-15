package com.shekhar._acres.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shekhar._acres.models.Admin;
import com.shekhar._acres.models.Cart;
import com.shekhar._acres.models.Flats;
import com.shekhar._acres.repositories.AdminRepository;
import com.shekhar._acres.responsewrapper.ResponseWrapper;

@Service
public class AdminService {
	
	@Autowired
    private AdminRepository adminRepository;
	
	@Autowired
	ResponseWrapper responseWrapper;

    public Admin registerAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin loginAdmin(String username, String password) {
        return adminRepository.findByUsername(username)
                .filter(admin -> admin.getPassword().equals(password))
                .orElse(null);
    }
    
    public Optional<Admin> findAdminById(Long id) {
        return adminRepository.findById(id);
    }
    
    public ResponseEntity<?> UpdateAdminById(Long id,Admin admin)
	{
		Optional<Admin> existingAdmin =  adminRepository.findById(id);
		
		if(existingAdmin.isPresent())
		{
			admin.setId(id);
			Admin updatedAdmin = adminRepository.save(admin);
			responseWrapper.setMessage("following admin updated");
			responseWrapper.setData(updatedAdmin);
			return new ResponseEntity<> (responseWrapper, HttpStatus.OK);
		}
		responseWrapper.setMessage("unable to update flat");
		responseWrapper.setData(null);
		return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_ACCEPTABLE);
	}
    
}

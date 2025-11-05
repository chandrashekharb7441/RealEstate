package com.shekhar._acres.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shekhar._acres.models.Admin;
import com.shekhar._acres.models.Flats;
import com.shekhar._acres.services.AdminService;

@RestController
@RequestMapping("99acres")
// @CrossOrigin(origins = {"https://nine9aresfe.onrender.com", "http://localhost:3000"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
	
	@Autowired
    private AdminService adminService;

    @PostMapping("/admin/register")
    public ResponseEntity<Admin> register(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.registerAdmin(admin));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<Admin> login(@RequestBody Admin admin) {
        Admin loggedInAdmin = adminService.loginAdmin(admin.getUsername(), admin.getPassword());
        if (loggedInAdmin != null) {
            return ResponseEntity.ok(loggedInAdmin);
        }
        return ResponseEntity.status(401).body(null);
    }
    
    @GetMapping("/admin/profile/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Optional<Admin> adminOptional = adminService.findAdminById(id);
        if (adminOptional.isPresent()) {
            return ResponseEntity.ok(adminOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    } 
    
    @PutMapping("/admin/profile-update/{id}")
	public ResponseEntity<?> UpdateAdminById(@PathVariable Long id,@RequestBody Admin admin)
	{
		return adminService.UpdateAdminById(id, admin);
	}
    
    



}

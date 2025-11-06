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
import com.shekhar._acres.models.User;
import com.shekhar._acres.services.AdminService;
import com.shekhar._acres.services.UserService;

@RestController
@RequestMapping("99acers")
// @CrossOrigin(origins = {"https://nine9aresfe.onrender.com", "http://localhost:3000"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/user/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User loggedInUser = userService.loginUser(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        }
        return ResponseEntity.status(401).body(null);
    }
    
    @GetMapping("/user/profile/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findUserById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    } 
    
    @PutMapping("/user/profile-update/{id}")
	public ResponseEntity<?> UpdateUserById(@PathVariable Long id,@RequestBody User user)
	{
		return userService.UpdateUserById(id, user);
	}
}

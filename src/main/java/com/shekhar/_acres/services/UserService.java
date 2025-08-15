package com.shekhar._acres.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shekhar._acres.models.Admin;
import com.shekhar._acres.models.User;
import com.shekhar._acres.repositories.AdminRepository;
import com.shekhar._acres.repositories.UserRepository;
import com.shekhar._acres.responsewrapper.ResponseWrapper;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	ResponseWrapper responseWrapper;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .orElse(null);
    }
    
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public ResponseEntity<?> UpdateUserById(Long id,User user)
	{
		Optional<User> existingUser =  userRepository.findById(id);
		
		if(existingUser.isPresent())
		{
			user.setId(id);
			User updatedUser = userRepository.save(user);
			responseWrapper.setMessage("following user updated");
			responseWrapper.setData(updatedUser);
			return new ResponseEntity<> (responseWrapper, HttpStatus.OK);
		}
		responseWrapper.setMessage("unable to update flat");
		responseWrapper.setData(null);
		return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_ACCEPTABLE);
	}
}

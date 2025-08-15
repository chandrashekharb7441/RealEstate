package com.shekhar._acres.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shekhar._acres.models.Cart;
import com.shekhar._acres.models.Flats;
import com.shekhar._acres.repositories.CartRepository;
import com.shekhar._acres.repositories.FlatRepository;
import com.shekhar._acres.responsewrapper.ResponseWrapper;

@Service
public class CartService {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ResponseWrapper responseWrapper;
	
	public ResponseEntity<?> addFlat(Cart carts) {
	    // Check if the size is null or empty
	    if (carts.getSize() == null || carts.getSize().isEmpty()) {
	        responseWrapper.setMessage("Size is required for the flat.");
	        responseWrapper.setData(null);  // You can set this to null or an empty object if needed
	        return new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);  // 400 Bad Request
	    }

	    // Save the cart if size is not null
	    Cart addflat = cartRepository.save(carts);
	    responseWrapper.setMessage("Following Flat Added");
	    responseWrapper.setData(addflat);
	    
	    return new ResponseEntity<>(responseWrapper, HttpStatus.CREATED);  // 201 Created
	}

	
	public ResponseEntity<?> getFlatsByUserId(Integer userId)
	{
	    List<Cart> carts = cartRepository.findByUserId(userId);
	    System.out.println(carts);
	    if (carts.isEmpty()) {
	        responseWrapper.setMessage("No flats found for the given User ID");
	        responseWrapper.setData(null);
	        return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
	    } else {
	        responseWrapper.setMessage("Flats found for User ID: " + userId);
	        responseWrapper.setData(carts);
	        return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
	    }
	}
	
	public ResponseEntity<?> deleteItemById(Long id)
	{
		cartRepository.deleteById(id);
		responseWrapper.setMessage("Item with Id :" + id + " deleted");
		responseWrapper.setData(null);
		return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
	}
}

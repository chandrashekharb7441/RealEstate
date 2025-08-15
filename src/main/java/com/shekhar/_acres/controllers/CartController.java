package com.shekhar._acres.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shekhar._acres.models.Cart;
import com.shekhar._acres.services.CartService;

@RestController
@RequestMapping("99acers")
@CrossOrigin("*")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/cart")
	public ResponseEntity<?> addFlat(@RequestBody Cart carts)
	{
		return cartService.addFlat(carts);
	}
	
	@GetMapping("/cart/user/{userId}")
	public ResponseEntity<?> getFlatsByUserId(@PathVariable Integer userId) {
		return cartService.getFlatsByUserId(userId);
	}
	
	@DeleteMapping("/cart/{id}")
	public ResponseEntity<?> deleteItemById(@PathVariable Long id)
	{
		return cartService.deleteItemById(id);
	}
	
	
}

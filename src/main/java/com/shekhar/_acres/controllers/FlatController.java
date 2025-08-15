package com.shekhar._acres.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shekhar._acres.models.Flats;
import com.shekhar._acres.services.FlatService;

@RestController
@RequestMapping("/99acres")
@CrossOrigin("*")
public class FlatController {
	
	@Autowired
	FlatService flatService;
	
	@PostMapping("/flat")
	public ResponseEntity<?> addFlat(@RequestBody Flats flats)
	{
		return flatService.addFlat(flats);
	}
	
	@GetMapping("/flats")
	public ResponseEntity<?> getAllFlats()
	{
		return flatService.getAllFlats();
	}
	
	@DeleteMapping("/flats/{id}")
	public ResponseEntity<?> deleteFlatById(@PathVariable Long id)
	{
		return flatService.deleteFlatById(id);
	}
	
	@GetMapping("/flats/{id}")
	public ResponseEntity<?> getFlatById(@PathVariable Long id)
	{
		return flatService.getFlatById(id);
	}
	
	@PutMapping("/flats/{id}")
	public ResponseEntity<?> UpdateFlatById(@PathVariable Long id,@RequestBody Flats flats)
	{
		return flatService.UpdateFlatById(id, flats);
	}
	
	@GetMapping("/flats/filter/{filteringOrder}")
	public ResponseEntity<?> getFilteredFlatByPrice(@PathVariable String filteringOrder)
	{
		return flatService.getFilteredFlatByPrice(filteringOrder);
	}
	
	@GetMapping("/flats/sort/{size}")
	public ResponseEntity<?>getSortedFlatsBySize(@PathVariable String size)
	{
		return flatService.getSortedFlatsBySize(size);
	}
	
	@GetMapping("/flats/admin/{adminId}")
    public ResponseEntity<?> getFlatsByAdminId(@PathVariable Integer adminId) {
        return flatService.getFlatsByAdminId(adminId);
    }
}

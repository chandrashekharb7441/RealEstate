package com.shekhar._acres.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.shekhar._acres.models.Flats;
import com.shekhar._acres.models.Land;
import com.shekhar._acres.services.LandService;

@RestController
@RequestMapping("/99acres")
@CrossOrigin("*")
public class LandController {
	
	@Autowired
	LandService landService;
	
	@PostMapping("/land")
	public ResponseEntity<?> addLand(@RequestBody Land lands)
	{
		return landService.addLand(lands);
	}
	
	@GetMapping("/lands")
	public ResponseEntity<?> getAllLands()
	{
		return landService.getAllLands();
	}
	
	@DeleteMapping("/land/{id}")
	public ResponseEntity<?> deleteLandById(@PathVariable Long id)
	{
		return landService.deleteLandById(id);
	}
	
	@GetMapping("/lands/{id}")
	public ResponseEntity<?> getLandById(@PathVariable Long id)
	{
		return landService.getLandById(id);
	}
	
	@PutMapping("/lands/{id}")
	public ResponseEntity<?> UpdateLandById(@PathVariable Long id,@RequestBody Land lands)
	{
		return landService.UpdateLandById(id, lands);
	}
	
	@GetMapping("/lands/filter/{filteringOrder}")
	public ResponseEntity<?> getFilteredLandByPrice(@PathVariable String filteringOrder)
	{
		return landService.getFilteredLandByPrice(filteringOrder);
	}
	
	@GetMapping("/lands/sort/{size}")
	public ResponseEntity<?>getSortedLandsBySize(@PathVariable String size)
	{
		return landService.getSortedLandsBySize(size);
	}
	
	@GetMapping("/lands/admin/{adminId}")
    public ResponseEntity<?> getLandsByAdminId(@PathVariable Integer adminId) {
        return landService.getLandsByAdminId(adminId);
    }
	
}

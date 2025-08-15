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
import com.shekhar._acres.models.Plot;
import com.shekhar._acres.repositories.PlotRepository;
import com.shekhar._acres.services.FlatService;
import com.shekhar._acres.services.PlotService;

@RestController
@RequestMapping("/99acres")
@CrossOrigin("*")
public class PlotController {
	
	@Autowired
	PlotService plotService;
	
	@PostMapping("/plot")
	public ResponseEntity<?> addPlot(@RequestBody Plot plots)
	{
		return plotService.addPlot(plots);
	}
	
	@GetMapping("/plots")
	public ResponseEntity<?> getAllPlots()
	{
		return plotService.getAllPlots();
	}
	
	@DeleteMapping("/plot/{id}")
	public ResponseEntity<?> deletePlotById(@PathVariable Long id)
	{
		return plotService.deletePlotById(id);
	}
	
	@GetMapping("/plots/{id}")
	public ResponseEntity<?> getPlotById(@PathVariable Long id)
	{
		return plotService.getPlotById(id);
	}
	
	@PutMapping("/plots/{id}")
	public ResponseEntity<?> UpdatePlotById(@PathVariable Long id,@RequestBody Plot plots)
	{
		return plotService.UpdatePlotById(id, plots);
	}
	
	@GetMapping("/plots/filter/{filteringOrder}")
	public ResponseEntity<?> getFilteredPlotByPrice(@PathVariable String filteringOrder)
	{
		return plotService.getFilteredPlotByPrice(filteringOrder);
	}
	
	@GetMapping("/plots/sort/{size}")
	public ResponseEntity<?>getSortedPlotsBySize(@PathVariable String size)
	{
		return plotService.getSortedPlotsBySize(size);
	}
	
	@GetMapping("/plots/admin/{adminId}")
    public ResponseEntity<?> getPlotsByAdminId(@PathVariable Integer adminId) {
        return plotService.getPlotsByAdminId(adminId);
    }
}

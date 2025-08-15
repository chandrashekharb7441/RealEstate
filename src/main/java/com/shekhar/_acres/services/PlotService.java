package com.shekhar._acres.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shekhar._acres.models.Plot;
import com.shekhar._acres.repositories.PlotRepository;
import com.shekhar._acres.responsewrapper.ResponseWrapper;

@Service
public class PlotService {
	@Autowired
	 PlotRepository plotRepository;
	
	@Autowired
	ResponseWrapper responseWrapper;
	
	public ResponseEntity<?> addPlot(Plot plot)
	{
		Plot addplot = plotRepository.save(plot);
		responseWrapper.setMessage("Following Plot Added");
		responseWrapper.setData(addplot);
		return new ResponseEntity<> (responseWrapper , HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> getAllPlots()
	{
		List<Plot> plots = plotRepository.findAll();
		if(plots.size()==0)
		{
			responseWrapper.setMessage("There are no plots! plese add some");
			responseWrapper.setData(null);
			return new ResponseEntity<> (responseWrapper, HttpStatus.NOT_FOUND);
		}
		else
		{
			responseWrapper.setMessage("Following Plot Added");
			responseWrapper.setData(plots);
			return new ResponseEntity<> (responseWrapper, HttpStatus.FOUND);
		}
	}
	
	public ResponseEntity<?> deletePlotById(Long id)
	{
		plotRepository.deleteById(id);
		responseWrapper.setMessage("plot with Id :" + id + " deleted");
		responseWrapper.setData(null);
		return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
	}
	
	public ResponseEntity<?> getPlotById(Long id)
	{
		Optional<Plot> plotData = plotRepository.findById(id);
		if(plotData.isPresent())
		{
			Plot plats = plotData.get();
			responseWrapper.setMessage("plot with id"+ id +" found");
			responseWrapper.setData(plotData);
			return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
		}
		else
		{
			responseWrapper.setMessage("Plot with Id:" + id +" not found");
			responseWrapper.setData(null);
			return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<?> UpdatePlotById(Long id,Plot plots)
	{
		Optional<Plot> existingPlot =  plotRepository.findById(id);
		
		if(existingPlot.isPresent())
		{
			plots.setId(id);
			plots.setCreatedAt(existingPlot.get().getCreatedAt());
			Plot updatedPlot = plotRepository.save(plots);
			responseWrapper.setMessage("following plot updated");
			responseWrapper.setData(updatedPlot);
			return new ResponseEntity<> (responseWrapper, HttpStatus.OK);
		}
		responseWrapper.setMessage("unable to update plot");
		responseWrapper.setData(null);
		return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_ACCEPTABLE);
	}
	
	public ResponseEntity<?> getFilteredPlotByPrice(String filteringOrder)
	{
		if(filteringOrder.equals("asc"))
		{
			List<Plot> plots=plotRepository.findAllByOrderByPriceAsc();
			responseWrapper.setMessage("Following plots found in asceding order");
			responseWrapper.setData(plots);
			return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
		}
		else
		{
			List<Plot> plots=plotRepository.findAllByOrderByPriceDesc();
			responseWrapper.setMessage("Following plots found in desceding order");
			responseWrapper.setData(plots);
			return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
		}
	}
	
	public ResponseEntity<?>getSortedPlotsBySize(String size)
	{
		List<Plot> plotData = plotRepository.findBySize(size);
		if(plotData.isEmpty())
		{
			responseWrapper.setData(null);
			responseWrapper.setMessage("there are no plots on this size");
			return new ResponseEntity<> (responseWrapper, HttpStatus.NOT_FOUND);
		}
		else
		{
			responseWrapper.setData(plotData);
			responseWrapper.setMessage("found plots base on size");
			return new ResponseEntity<> (responseWrapper, HttpStatus.FOUND);
		}
		
	}
	
	public ResponseEntity<?> getPlotsByAdminId(Integer adminId)
	{
	    List<Plot> plots = plotRepository.findByAdminId(adminId);
	    if (plots.isEmpty()) {
	        responseWrapper.setMessage("No plots found for the given Admin ID");
	        responseWrapper.setData(null);
	        return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
	    } else {
	        responseWrapper.setMessage("Plots found for Admin ID: " + adminId);
	        responseWrapper.setData(plots);
	        return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
	    }
	}
}

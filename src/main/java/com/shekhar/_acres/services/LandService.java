package com.shekhar._acres.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shekhar._acres.models.Flats;
import com.shekhar._acres.models.Land;
import com.shekhar._acres.models.Plot;
import com.shekhar._acres.repositories.LandRepository;
import com.shekhar._acres.responsewrapper.ResponseWrapper;

@Service
public class LandService {
	
	@Autowired
	LandRepository landRepository;
	
	@Autowired
	ResponseWrapper responseWrapper;
	
	public ResponseEntity<?> addLand(Land lands)
	{
		Land addland = landRepository.save(lands);
		responseWrapper.setMessage("Following Land Added");
		responseWrapper.setData(addland);
		return new ResponseEntity<> (responseWrapper , HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> getAllLands()
	{
		List<Land> lands = landRepository.findAll();
		if(lands.size()==0)
		{
			responseWrapper.setMessage("There are no lands! plese add some");
			responseWrapper.setData(null);
			return new ResponseEntity<> (responseWrapper, HttpStatus.NOT_FOUND);
		}
		else
		{
			responseWrapper.setMessage("Following Land Added");
			responseWrapper.setData(lands);
			return new ResponseEntity<> (responseWrapper, HttpStatus.FOUND);
		}
	}
	
	public ResponseEntity<?> deleteLandById(Long id)
	{
		landRepository.deleteById(id);
		responseWrapper.setMessage("land with Id :" + id + " deleted");
		responseWrapper.setData(null);
		return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
	}
	
	public ResponseEntity<?> getLandById(Long id)
	{
		Optional<Land> landData = landRepository.findById(id);
		if(landData.isPresent())
		{
			Land lands = landData.get();
			responseWrapper.setMessage("land with id"+ id +" found");
			responseWrapper.setData(landData);
			return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
		}
		else
		{
			responseWrapper.setMessage("Land with Id:" + id +" not found");
			responseWrapper.setData(null);
			return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<?> UpdateLandById(Long id,Land lands)
	{
		Optional<Land> existingLand =  landRepository.findById(id);
		
		if(existingLand.isPresent())
		{
			lands.setId(id);
			lands.setCreatedAt(existingLand.get().getCreatedAt());
			Land updatedLand = landRepository.save(lands);
			responseWrapper.setMessage("following land updated");
			responseWrapper.setData(updatedLand);
			return new ResponseEntity<> (responseWrapper, HttpStatus.OK);
		}
		responseWrapper.setMessage("unable to update land");
		responseWrapper.setData(null);
		return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_ACCEPTABLE);
	}
	
	public ResponseEntity<?> getFilteredLandByPrice(String filteringOrder)
	{
		if(filteringOrder.equals("asc"))
		{
			List<Land> lands=landRepository.findAllByOrderByPriceAsc();
			responseWrapper.setMessage("Following lands found in asceding order");
			responseWrapper.setData(lands);
			return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
		}
		else
		{
			List<Land> lands=landRepository.findAllByOrderByPriceDesc();
			responseWrapper.setMessage("Following lands found in desceding order");
			responseWrapper.setData(lands);
			return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
		}
	}
	
	public ResponseEntity<?>getSortedLandsBySize(String size)
	{
		List<Land> landData = landRepository.findBySize(size);
		if(landData.isEmpty())
		{
			responseWrapper.setData(null);
			responseWrapper.setMessage("there are no lands on this size");
			return new ResponseEntity<> (responseWrapper, HttpStatus.NOT_FOUND);
		}
		else
		{
			responseWrapper.setData(landData);
			responseWrapper.setMessage("found lands base on size");
			return new ResponseEntity<> (responseWrapper, HttpStatus.FOUND);
		}
		
	}
	
	public ResponseEntity<?> getLandsByAdminId(Integer adminId)
	{
	    List<Land> lands = landRepository.findByAdminId(adminId);
	    if (lands.isEmpty()) {
	        responseWrapper.setMessage("No lands found for the given Admin ID");
	        responseWrapper.setData(null);
	        return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
	    } else {
	        responseWrapper.setMessage("Lands found for Admin ID: " + adminId);
	        responseWrapper.setData(lands);
	        return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
	    }
	}
}

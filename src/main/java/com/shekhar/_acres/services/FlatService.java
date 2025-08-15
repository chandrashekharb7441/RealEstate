package com.shekhar._acres.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shekhar._acres.models.Flats;
import com.shekhar._acres.repositories.FlatRepository;
import com.shekhar._acres.responsewrapper.ResponseWrapper;


@Service
public class FlatService {
	
	@Autowired
	FlatRepository flatRepository;
	
	@Autowired
	ResponseWrapper responseWrapper;
	
	public ResponseEntity<?> addFlat(Flats flats)
	{
		Flats addflat = flatRepository.save(flats);
		responseWrapper.setMessage("Following Flat Added");
		responseWrapper.setData(addflat);
		return new ResponseEntity<> (responseWrapper , HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> getAllFlats()
	{
		List<Flats> flats = flatRepository.findAll();
		if(flats.size()==0)
		{
			responseWrapper.setMessage("There are no flats! plese add some");
			responseWrapper.setData(null);
			return new ResponseEntity<> (responseWrapper, HttpStatus.NOT_FOUND);
		}
		else
		{
			responseWrapper.setMessage("Following Flat Added");
			responseWrapper.setData(flats);
			return new ResponseEntity<> (responseWrapper, HttpStatus.FOUND);
		}
	}
	
	public ResponseEntity<?> deleteFlatById(Long id)
	{
		flatRepository.deleteById(id);
		responseWrapper.setMessage("flat with Id :" + id + " deleted");
		responseWrapper.setData(null);
		return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
	}
	
	public ResponseEntity<?> getFlatById(Long id)
	{
		Optional<Flats> flatData = flatRepository.findById(id);
		if(flatData.isPresent())
		{
//			Flats flats = flatData.get();
			responseWrapper.setMessage("flat with id"+ id +" found");
			responseWrapper.setData(flatData);
			return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
		}
		else
		{
			responseWrapper.setMessage("Flat with Id:" + id +" not found");
			responseWrapper.setData(null);
			return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<?> UpdateFlatById(Long id,Flats flats)
	{
		Optional<Flats> existingFlat =  flatRepository.findById(id);
		
		if(existingFlat.isPresent())
		{
			flats.setId(id);
			flats.setCreatedAt(existingFlat.get().getCreatedAt());
			Flats updatedFlat = flatRepository.save(flats);
			responseWrapper.setMessage("following flat updated");
			responseWrapper.setData(updatedFlat);
			return new ResponseEntity<> (responseWrapper, HttpStatus.OK);
		}
		responseWrapper.setMessage("unable to update flat");
		responseWrapper.setData(null);
		return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_ACCEPTABLE);
	}
	
	public ResponseEntity<?> getFilteredFlatByPrice(String filteringOrder)
	{
		if(filteringOrder.equals("asc"))
		{
			List<Flats> flats=flatRepository.findAllByOrderByPriceAsc();
			responseWrapper.setMessage("Following flats found in asceding order");
			responseWrapper.setData(flats);
			return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
		}
		else
		{
			List<Flats> flats=flatRepository.findAllByOrderByPriceDesc();
			responseWrapper.setMessage("Following flats found in desceding order");
			responseWrapper.setData(flats);
			return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
		}
	}
	
	public ResponseEntity<?>getSortedFlatsBySize(String size)
	{
		List<Flats> flatsData = flatRepository.findBySize(size);
		if(flatsData.isEmpty())
		{
			responseWrapper.setData(null);
			responseWrapper.setMessage("there are no flats on this size");
			return new ResponseEntity<> (responseWrapper, HttpStatus.NOT_FOUND);
		}
		else
		{
			responseWrapper.setData(flatsData);
			responseWrapper.setMessage("found flats base on size");
			return new ResponseEntity<> (responseWrapper, HttpStatus.FOUND);
		}
		
	}
	
	public ResponseEntity<?> getFlatsByAdminId(Integer adminId)
	{
	    List<Flats> flats = flatRepository.findByAdminId(adminId);
	    if (flats.isEmpty()) {
	        responseWrapper.setMessage("No flats found for the given Admin ID");
	        responseWrapper.setData(null);
	        return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
	    } else {
	        responseWrapper.setMessage("Flats found for Admin ID: " + adminId);
	        responseWrapper.setData(flats);
	        return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
	    }
	}

	
}

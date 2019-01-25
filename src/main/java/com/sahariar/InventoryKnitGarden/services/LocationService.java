package com.sahariar.InventoryKnitGarden.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.InventoryKnitGarden.models.Location;
import com.sahariar.InventoryKnitGarden.repositories.LocationRepository;
import com.sahariar.InventoryKnitGarden.requests.LocationRequest;

@Service
public class LocationService {

	@Autowired
	LocationRepository locationRepositoy;
	
	
	
	public long createNewLocation(LocationRequest request)
	{
		
		Location location =new Location();
		location.setName(request.getName());
		location.setAddress(request.getAddress());
		
		try {
			locationRepositoy.save(location);
			return location.getId();
		}
		
		catch(Exception e)
		{
			return -1L;
		}
		
	}
	public List<Location> getAllLocation()
	{
		return locationRepositoy.findAll();
	}
}

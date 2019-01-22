package com.sahariar.InventoryKnitGarden.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.InventoryKnitGarden.models.Unit;
import com.sahariar.InventoryKnitGarden.repositories.UnitRepository;
import com.sahariar.InventoryKnitGarden.requests.UnitRequest;

@Service
public class UnitService {

	
	@Autowired
	UnitRepository unitRepository;
	
	
		
	    public Long createNewUnit(UnitRequest request)
	    {
	    	Unit unit=new Unit();
	    	unit.setName(request.getName());
	    	unit.setSymbol(request.getSymbol());
	    	unit.setDescription(request.getDescription());
	    	
	    	try {
	    		
	    		unitRepository.save(unit);
	    		return unit.getId();
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    		return -1L;
	    	}
	    	
	    }
	
	     
	  
	    
	
	    public List<Unit> getAllUnits()
	    {
	    	return unitRepository.findAll();
	    }
	
}

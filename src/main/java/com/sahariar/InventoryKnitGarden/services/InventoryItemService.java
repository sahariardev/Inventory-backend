package com.sahariar.InventoryKnitGarden.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.InventoryKnitGarden.models.InventoryItem;
import com.sahariar.InventoryKnitGarden.repositories.InventoryItemRepository;
import com.sahariar.InventoryKnitGarden.requests.InventoryItemRequest;

@Service
public class InventoryItemService {

	
	@Autowired
	InventoryItemRepository inventoryItemrepo;
	
	public long createNewInventoryItem(InventoryItemRequest request)
	{
		InventoryItem item=new InventoryItem();
		item.setCode("live");
		item.setColor_code(request.getColor_code());
		item.setDescription(request.getDescription());
		item.setExtra(request.getExtra());
		item.setImage(request.getImage());
		item.setName(request.getName());
		item.setOthers(request.getOthers());
		
		try {
			
			inventoryItemrepo.save(item);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return -1L;
			
			
	}
	
}


package com.sahariar.InventoryKnitGarden.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.InventoryKnitGarden.models.InventoryItem;
import com.sahariar.InventoryKnitGarden.repositories.InventoryItemRepository;
import com.sahariar.InventoryKnitGarden.requests.InventoryItemRequest;

@Service
public class InventoryItemService {

	
	@Autowired
	InventoryItemRepository inventoryItemrepo;
	
	@Autowired
	CategoryService categoryService;
	
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
		item.setCategory(categoryService.getOne(request.getCategory_id()));
		System.out.println(item.getCategory());
		
		
		try {
			
			inventoryItemrepo.save(item);
			return item.getId();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return -1L;			
	}
	
	public boolean saveItemIMage(long id, String image)
	{
		InventoryItem item=inventoryItemrepo.getOne(id);
		if(item != null)
		{
			item.setImage(image);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public long createNewInventoryItem(InventoryItemRequest request,String username)
	{
		InventoryItem item=new InventoryItem();
		item.setCode("live");
		item.setColor_code(request.getColor_code());
		item.setDescription(request.getDescription());
		item.setExtra(request.getExtra());
		item.setImage(request.getImage());
		item.setName(request.getName());
		item.setUpdatedBy(username);
		item.setCratedBy(username);
		item.setOthers(request.getOthers());
		item.setCategory(categoryService.getOne(request.getCategory_id()));
		System.out.println(item.getCategory());
		
		try {
			
			inventoryItemrepo.save(item);
			return item.getId();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return -1L;			
	}
	public List<InventoryItem> getAll()
	{
		return inventoryItemrepo.findAll();
	}
	
}


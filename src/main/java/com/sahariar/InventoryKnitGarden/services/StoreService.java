package com.sahariar.InventoryKnitGarden.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.InventoryKnitGarden.models.InventoryItem;
import com.sahariar.InventoryKnitGarden.models.Location;
import com.sahariar.InventoryKnitGarden.models.Stage;
import com.sahariar.InventoryKnitGarden.models.Store;
import com.sahariar.InventoryKnitGarden.models.Unit;
import com.sahariar.InventoryKnitGarden.repositories.InventoryItemRepository;
import com.sahariar.InventoryKnitGarden.repositories.LocationRepository;
import com.sahariar.InventoryKnitGarden.repositories.StageRepository;
import com.sahariar.InventoryKnitGarden.repositories.StoreRepository;
import com.sahariar.InventoryKnitGarden.repositories.UnitRepository;
import com.sahariar.InventoryKnitGarden.requests.ItemMoveRequest;
import com.sahariar.InventoryKnitGarden.requests.StoreRequest;

@Service
public class StoreService {

	
	@Autowired
	StageRepository stageRepository;
	
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	UnitRepository unitRepository;
	
	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	InventoryItemRepository itemRepository;
	
	
	public Long createNewStore(StoreRequest request)
	{
		Store store=new Store();		
		Stage stage=stageRepository.getOne(request.getStage_id());
		Unit unit =unitRepository.getOne(request.getUnit_id());
		Location location=locationRepository.getOne(request.getLocation_id());
		InventoryItem item=itemRepository.getOne(request.getItem_id());
		
		store.setUnit(unit);
		store.setStage(stage);
		store.setLocation(location);
		store.setItem(item);
		store.setQuantity(request.getQuantity());
		
		try {
			storeRepository.save(store);
			return store.getId();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1L;
		}
				
	}
	
	
	//get all stores info
	
	public List<Store> getAllStores()
	{
		return storeRepository.findAll();
	}
	
	public Store getOne(Long id)
	{
		return storeRepository.getOne(id);
	}
	
	
	public boolean moveRequestHandler(ItemMoveRequest request) throws Exception
	{
		//first get the current store
		//deduct quntity from that and add the new store
		Store old=storeRepository.getOne(request.getFrom_store());
		double newQuantity=old.getQuantity()-request.getQuntity();
		if(newQuantity<0) throw new Exception();
		old.setQuantity(newQuantity);
		Stage stage=stageRepository.getOne(request.getTo_stage());
		Unit unit =old.getUnit();
		Location location=locationRepository.getOne(request.getTo_location());
		InventoryItem item=old.getItem();
		Store store=new Store();
		store.setUnit(unit);
		store.setStage(stage);
		store.setLocation(location);
		store.setItem(item);
		store.setQuantity(request.getQuntity());
		String info=old.getExtra()+"$QQ$From Store::"+old.getId();
		store.setExtra(info);
		store.setDescription(request.getDescription());
		
		try
		{
			storeRepository.save(old);
			storeRepository.save(store);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}	
	
}

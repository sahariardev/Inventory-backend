package com.sahariar.InventoryKnitGarden.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.InventoryKnitGarden.models.InventoryItem;
import com.sahariar.InventoryKnitGarden.models.Purchase;
import com.sahariar.InventoryKnitGarden.models.Style;
import com.sahariar.InventoryKnitGarden.models.Unit;
import com.sahariar.InventoryKnitGarden.repositories.InventoryItemRepository;
import com.sahariar.InventoryKnitGarden.repositories.PurchaseRepository;
import com.sahariar.InventoryKnitGarden.repositories.StyleRepository;
import com.sahariar.InventoryKnitGarden.repositories.UnitRepository;
import com.sahariar.InventoryKnitGarden.requests.PurchaseRequest;

@Service
public class PurchaseService {

	@Autowired
	PurchaseRepository purchaseRepository;	
	@Autowired
	UnitRepository unitRepository;
	@Autowired
	InventoryItemRepository itemRepository;
	
	@Autowired
	StyleRepository styleRepository; 
	
	public Long createNewPurchase(PurchaseRequest request)
	{
	     Unit unit=unitRepository.getOne(request.getUnit_id());
	     InventoryItem item=itemRepository.getOne(request.getItem_id());
	     Purchase purchase=new Purchase();
	     purchase.setDelivaryDate(request.getDelivaryDate());
	     purchase.setPurchaseDate(request.getPurchaseDate());
	     purchase.setUnit(unit);
	     purchase.setItem(item);
	     purchase.setDescription(request.getDescription());
	     purchase.setSellerName(request.getSellerName());
	     purchase.setSellerAddress(request.getSellerAddress());
	     purchase.setQuantity(request.getQuantity());
	     purchase.setReceiptNumber(request.getReceiptNumber());  
	     purchase.setDiscount(request.getDiscount());
	     purchase.setPrice_per_unit(request.getPrice_per_unit());
	     Style style=styleRepository.getOne(request.getStyle_id());
	     purchase.setStyle(style);
	     try {
	    	 purchaseRepository.save(purchase);
	    	 return purchase.getId();  	 
	     }
	     catch(Exception e)
	     {
	    	 e.printStackTrace();
	    	 return -1L;
	     }
	     
	}
	
	public List<Purchase> getAllPurchases()
	{
		return purchaseRepository.findAll();
	}
}

package com.sahariar.InventoryKnitGarden.requests;

import com.sahariar.InventoryKnitGarden.models.InventoryItem;
import com.sahariar.InventoryKnitGarden.models.Location;
import com.sahariar.InventoryKnitGarden.models.Store;
import com.sahariar.InventoryKnitGarden.models.Unit;

public class StoreRequest {
	private Long store_id;
	private Long location_id;
	private Long item_id;
	private Long unit_id;
	private double quantity;
	public Long getStore_id() {
		return store_id;
	}
	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}
	public Long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	public Long getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(Long unit_id) {
		this.unit_id = unit_id;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	
	

}

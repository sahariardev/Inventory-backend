package com.sahariar.InventoryKnitGarden.requests;

public class ItemMoveRequest {

	private long item_id;
	private long from_store;
	private long to_stage;
	private double quntity;
	private long to_location;
	private String description;
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}
	public long getFrom_store() {
		return from_store;
	}
	public void setFrom_store(long from_store) {
		this.from_store = from_store;
	}
	public long getTo_stage() {
		return to_stage;
	}
	public void setTo_stage(long to_stage) {
		this.to_stage = to_stage;
	}
	public double getQuntity() {
		return quntity;
	}
	public void setQuntity(double quntity) {
		this.quntity = quntity;
	}
	public long getTo_location() {
		return to_location;
	}
	public void setTo_location(long to_location) {
		this.to_location = to_location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	
	
	
}

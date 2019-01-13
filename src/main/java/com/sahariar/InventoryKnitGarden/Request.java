package com.sahariar.InventoryKnitGarden;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int id;
	private String sender;
	private String inventory_item;
	private double unit;
	private double quantity;
	private double pricePerUnitItem;
	private String receiver;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getInventory_item() {
		return inventory_item;
	}
	public void setInventory_item(String inventory_item) {
		this.inventory_item = inventory_item;
	}
	public double getUnit() {
		return unit;
	}
	public void setUnit(double unit) {
		this.unit = unit;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getPricePerUnitItem() {
		return pricePerUnitItem;
	}
	public void setPricePerUnitItem(double pricePerUnitItem) {
		this.pricePerUnitItem = pricePerUnitItem;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	
	
}

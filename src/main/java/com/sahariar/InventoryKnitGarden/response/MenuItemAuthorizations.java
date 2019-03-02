package com.sahariar.InventoryKnitGarden.response;

public class MenuItemAuthorizations {

	public boolean InventoryItems=false;
	public boolean buyer=false;
	public boolean project=false;
	public boolean style=false;
	public boolean unit=false;
	public boolean category=false;
	public boolean location=false;
	public boolean stage=false;
	public boolean inventory=false;
	public boolean booking=false;
	public boolean purchase=false;
	public boolean isInventoryItems() {
		return InventoryItems;
	}
	public void setInventoryItems(boolean inventoryItems) {
		InventoryItems = inventoryItems;
	}
	public boolean isBuyer() {
		return buyer;
	}
	public void setBuyer(boolean buyer) {
		this.buyer = buyer;
	}
	public boolean isProject() {
		return project;
	}
	public void setProject(boolean project) {
		this.project = project;
	}
	public boolean isStyle() {
		return style;
	}
	public void setStyle(boolean style) {
		this.style = style;
	}
	public boolean isUnit() {
		return unit;
	}
	public void setUnit(boolean unit) {
		this.unit = unit;
	}
	public boolean isCategory() {
		return category;
	}
	public void setCategory(boolean category) {
		this.category = category;
	}
	public boolean isLocation() {
		return location;
	}
	public void setLocation(boolean location) {
		this.location = location;
	}
	public boolean isStage() {
		return stage;
	}
	public void setStage(boolean stage) {
		this.stage = stage;
	}
	public boolean isInventory() {
		return inventory;
	}
	public void setInventory(boolean inventory) {
		this.inventory = inventory;
	}
	public boolean isBooking() {
		return booking;
	}
	public void setBooking(boolean booking) {
		this.booking = booking;
	}
	public boolean isPurchase() {
		return purchase;
	}
	public void setPurchase(boolean purchase) {
		this.purchase = purchase;
	}
	@Override
	public String toString() {
		return "MenuItem [InventoryItems=" + InventoryItems + ", buyer=" + buyer + ", project=" + project + ", style="
				+ style + ", unit=" + unit + ", category=" + category + ", location=" + location + ", stage=" + stage
				+ ", inventory=" + inventory + ", booking=" + booking + ", purchase=" + purchase + "]";
	}
	
	
	
}

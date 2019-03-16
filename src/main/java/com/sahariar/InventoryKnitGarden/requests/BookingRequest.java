package com.sahariar.InventoryKnitGarden.requests;

import java.sql.Date;
public class BookingRequest {

	

	private double quantity;	
	private long unit_id;
	private long item_id;		
	private long style_id;
	private double priceperunit;
	private Date date;
	private String description;
	private String extra;

	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public long getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(long unit_id) {
		this.unit_id = unit_id;
	}
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}
	public long getStyle_id() {
		return style_id;
	}
	public void setStyle_id(long style_id) {
		this.style_id = style_id;
	}
	public double getPriceperunit() {
		return priceperunit;
	}
	public void setPriceperunit(double priceperunit) {
		this.priceperunit = priceperunit;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "BookingRequest [quantity=" + quantity + ", unit_id=" + unit_id + ", item_id=" + item_id + ", style_id="
				+ style_id + ", priceperunit=" + priceperunit + ", date=" + date + "]";
	}

	
	
}

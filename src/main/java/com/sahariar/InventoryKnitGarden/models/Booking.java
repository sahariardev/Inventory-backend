package com.sahariar.InventoryKnitGarden.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Booking extends BaseEntity {

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private double quantity;
	
	@OneToOne
	private Unit unit;
	@OneToOne
	private InventoryItem item;
	
	@OneToOne
	private Style style;
	private double priceperunit;
	private Date date;
	private String status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public InventoryItem getItem() {
		return item;
	}
	public void setItem(InventoryItem item) {
		this.item = item;
	}
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
}

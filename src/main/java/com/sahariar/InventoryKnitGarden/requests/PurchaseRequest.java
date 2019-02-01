package com.sahariar.InventoryKnitGarden.requests;

import java.sql.Date;

import javax.persistence.OneToOne;

import com.sahariar.InventoryKnitGarden.models.InventoryItem;
import com.sahariar.InventoryKnitGarden.models.Unit;

public class PurchaseRequest {
	
	private String receiptNumber;
	private String sellerName;
	private String sellerAddress;
	private Date purchaseDate;
	private Date delivaryDate;
	private long unit_id;
	private Long quantity;
	private long item_id;
	private String description;
	public String getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSellerAddress() {
		return sellerAddress;
	}
	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Date getDelivaryDate() {
		return delivaryDate;
	}
	public void setDelivaryDate(Date delivaryDate) {
		this.delivaryDate = delivaryDate;
	}
	public long getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(long unit_id) {
		this.unit_id = unit_id;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	

}

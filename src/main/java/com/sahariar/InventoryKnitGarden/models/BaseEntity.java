package com.sahariar.InventoryKnitGarden.models;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class BaseEntity {

	
	public String code;
	public String extra;
	public String description;
	public Date createdAt;
	public Date updatedAt;
	public User cratedBy;
	public User updatedBy;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public Date getCreatedAt() {
		return createdAt;
	}
	@PrePersist
	public void setCreatedAt() {
		if (this.createdAt == null) this.createdAt=new Date();
		if(this.updatedAt == null) this.updatedAt=new Date();
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	@PreUpdate
	public void setUpdatedAt() {
		this.updatedAt = new Date();
		
	}
	public User getCratedBy() {
		return cratedBy;
	}
	public void setCratedBy(User cratedBy) {
		this.cratedBy = cratedBy;
	}
	public User getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	
	
	
	
}

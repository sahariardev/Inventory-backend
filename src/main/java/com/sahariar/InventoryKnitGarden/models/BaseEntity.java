package com.sahariar.InventoryKnitGarden.models;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.lang.Nullable;

@MappedSuperclass
public class BaseEntity {

	@Nullable
	public String code;
	@Nullable
	public String extra;
	@Nullable
	public String description;
	@Nullable
	public Date createdAt;
	@Nullable
	public Date updatedAt;
	@Nullable
	public String cratedBy;
	@Nullable
	public String updatedBy;
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
	public String getCratedBy() {
		return cratedBy;
	}
	public void setCratedBy(String cratedBy) {
		this.cratedBy = cratedBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
    
	
	
	
	
	
	
}

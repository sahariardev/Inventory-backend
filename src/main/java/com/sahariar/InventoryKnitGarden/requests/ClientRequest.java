package com.sahariar.InventoryKnitGarden.requests;

public class ClientRequest {
	
	private String name;
	private String description;
	private long assigned_to;
	private String contact;
	private String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(long assigned_to) {
		this.assigned_to = assigned_to;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}

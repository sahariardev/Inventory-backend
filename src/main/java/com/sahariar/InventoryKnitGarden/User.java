package com.sahariar.InventoryKnitGarden;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	// Elements of TABLE
	private int id;
	private String email;
	private String Name;
	private String role;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
	
}

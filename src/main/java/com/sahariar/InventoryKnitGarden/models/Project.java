package com.sahariar.InventoryKnitGarden.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Project  extends BaseEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	
	@ManyToOne
	private Client client;

	
	@OneToMany
	private List<Style> styles;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Style> getStyles() {
		return styles;
	}

	public void setStyles(List<Style> styles) {
		this.styles = styles;
	}
	
	
	
	
	
	
}

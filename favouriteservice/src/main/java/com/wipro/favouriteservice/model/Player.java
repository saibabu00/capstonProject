package com.wipro.favouriteservice.model;

import org.springframework.data.annotation.Id;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

	@Id
	@JsonProperty("pid")

    private String pid;
    
	@JsonProperty("name")
    private String name;

    @JsonProperty("country")
    private String country;

	public Player() {
		super();
	}

	public Player(String pid ,String name, String country) {
		
		this.pid = pid;
	
		this.name = name;
		this.country = country;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Player [pid=" + pid + ", name=" + name + ", country=" + country + "]";
	}

	

	
	

	    
}

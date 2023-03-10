package com.stackroute.favouriteservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection="User")
public class User {

	@Id
	@JsonProperty("username")
	private String username;
	
	private List<Player> playerList;
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public User(String username, List<Player> playerList) {
		super();
		this.username = username;
		this.playerList = playerList;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", playerList=" + playerList + "]";
	}
	
	
	
	
	
	
}

package com.stackroute.favouriteservice.model;

import org.springframework.data.annotation.Id;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

	@Id
	@JsonProperty("playerName")

    private String playerName;
    
	@JsonProperty("matches")
    private int matches;
	
	@JsonProperty("span")
    private String span;
	
	@JsonProperty("runs")
    private int runs;
	
	@JsonProperty("hundreds")
    private int hundreds;
	
	@JsonProperty("fifties")
    private int fifties;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}

	public String getSpan() {
		return span;
	}

	public void setSpan(String span) {
		this.span = span;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getHundreds() {
		return hundreds;
	}

	public void setHundreds(int hundreds) {
		this.hundreds = hundreds;
	}

	public int getFifties() {
		return fifties;
	}

	public void setFifties(int fifties) {
		this.fifties = fifties;
	}

	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Player(String playerName, int matches, String span, int runs, int hundreds, int fifties) {
		super();
		this.playerName = playerName;
		this.matches = matches;
		this.span = span;
		this.runs = runs;
		this.hundreds = hundreds;
		this.fifties = fifties;
	}

	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", matches=" + matches + ", span=" + span + ", runs=" + runs
				+ ", hundreds=" + hundreds + ", fifties=" + fifties + "]";
	}
	
	
	

//    @JsonProperty("country")
//    private String country;

	

	

	

	
	
	

	    
}

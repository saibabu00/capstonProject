package com.wipro.playerlist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class Player {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	private String playerName;
	private String span;
	private int matches;
	private int runs;
	private int hundreds;
	private int fifties;
	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Player(int pid,String playerName, String span, int matches, int runs, int hundreds, int fifties) {
		super();
		this.pid=pid;
		this.playerName = playerName;
		this.span = span;
		this.matches = matches;
		this.runs = runs;
		this.hundreds = hundreds;
		this.fifties = fifties;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getSpan() {
		return span;
	}
	public void setSpan(String span) {
		this.span = span;
	}
	public int getMatches() {
		return matches;
	}
	public void setMatches(int matches) {
		this.matches = matches;
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
	@Override
	public String toString() {
		return "Player [pid=" + pid + ",playerName=" + playerName + ", span=" + span + ", matches=" + matches + ", runs=" + runs + ", hundreds="
				+ hundreds + ", fifties=" + fifties + "]";
	}
	

}

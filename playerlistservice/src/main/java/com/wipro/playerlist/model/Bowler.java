package com.wipro.playerlist.model;

//import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class Bowler {
	
	@Id
	private int pbid;
	private String bowlerName;
	private String span;
	private int wickets;
	private String matches;
	private int fiveWktHaul;
	
	public int getPbid() {
		return pbid;
	}
	public void setPbid(int pbid) {
		this.pbid = pbid;
	}
	public String getBowlerName() {
		return bowlerName;
	}
	public void setBowlerName(String bowlerName) {
		this.bowlerName = bowlerName;
	}
	public String getSpan() {
		return span;
	}
	public void setSpan(String span) {
		this.span = span;
	}
	public int getWickets() {
		return wickets;
	}
	public void setWickets(int wickets) {
		this.wickets = wickets;
	}
	public String getMatches() {
		return matches;
	}
	public void setMatches(String matches) {
		this.matches = matches;
	}
	public int getFiveWktHaul() {
		return fiveWktHaul;
	}
	public void setFiveWktHaul(int fiveWktHaul) {
		this.fiveWktHaul = fiveWktHaul;
	}
	public Bowler() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bowler(int pbid, String bowlerName, String span, int wickets, String matches, int fiveWktHaul) {
		super();
		this.pbid = pbid;
		this.bowlerName = bowlerName;
		this.span = span;
		this.wickets = wickets;
		this.matches = matches;
		this.fiveWktHaul = fiveWktHaul;
	}
	@Override
	public String toString() {
		return "Bowler [pbid=" + pbid + ", bowlerName=" + bowlerName + ", span=" + span + ", wickets=" + wickets
				+ ", matches=" + matches + ", fiveWktHaul=" + fiveWktHaul + "]";
	}
	
	
	

}

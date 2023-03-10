package com.wipro.admin.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bowler {
	
	@Id
	private int pbid;
	private String bowlerName;
	private String span;
	private int wickets;
	private String matches;
	private int fiveWktHaul;

}

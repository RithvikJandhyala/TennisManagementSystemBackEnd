package com.tennis.springboots.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "tenniscenter")
public class TennisCenter {
	@Id
	private String name;
	private String address;
	private int[] courts;
	//@ElementCollection(targetClass = Match.class)
	//@MapKeyClass(Integer.class)
	@Transient
	Map<Integer, Match> courtMatches = new HashMap<>();
	@Transient
	private ArrayList<Court> tcourts = new ArrayList<Court>() ;
	public TennisCenter() {}
	public TennisCenter(String name, String address, int[] courts, ArrayList<Court> tcourts  ) {
		super();
		this.name = name;
		this.address = address;
		this.courts = courts;
		for(int i=0;i<courts.length;i++) {
			courtMatches.put(courts[i], null);
		}
		this.tcourts = tcourts;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int[] getCourts() {
		return courts;
	}
	public void setCourts(int[] courts) {
		this.courts = courts;
	}
	
	public void assignMatchToCourt(int courtNumber, Match match) {
		courtMatches.put(courtNumber, match);
	}

	public void getCourtsStatus()
	{
		System.out.println("\nCourts Status");
		for(int i=0;i<courts.length;i++) {
			System.out.println("Court:"+courts[i] + ": " + courtMatches.get(courts[i]));
		}
		
	}
	
	public String getCourtStatus(int courtNumber)
	{
		Match match = courtMatches.get(courtNumber);
		if( match == null) 
			return "Available";
		else 
			return "Occupied: " + match;
	}
	
}

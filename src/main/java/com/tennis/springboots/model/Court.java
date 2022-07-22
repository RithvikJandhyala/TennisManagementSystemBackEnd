package com.tennis.springboots.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "court")
public class Court {
	@Id
	private int courtid;
	@OneToOne
	@JoinColumn(name="matchID")
	private Match currentMatch;
	private String location;
	private String status = "Available";
	
	public Court(int courtid, Match currentMatch, String location) {
		super();
		this.courtid = courtid;
		this.location = location;
	}
	public Court() {
		// TODO Auto-generated constructor stub
	}
	public int getCourtid() {
		return courtid;
	}
	public void setCourtid(int courtid) {
		this.courtid = courtid;
	}
	public Match getCurrentMatch() {
		return currentMatch;
	}
	
	public int getCurrentMatchID() {
		if(currentMatch == null)
			return 0;
		return currentMatch.getMatchID();
	} 
	
	public void setCurrentMatch(Match currentMatch) {
		this.currentMatch = currentMatch;
		if(this.currentMatch == null)
			status = "Available";
		else
			status = currentMatch.getStatus();
	}
	public String getStatus() {
		return status;
	}	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String toString()
	{
		return "<<Court: " + courtid + " Status: " + status + " Match: " + getCurrentMatchID() + ">>";
	}

}

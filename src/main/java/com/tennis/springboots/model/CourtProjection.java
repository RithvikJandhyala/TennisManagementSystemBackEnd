package com.tennis.springboots.model;
public class CourtProjection {
	
	private int courtID;
	
	private String status;
	private String match;	
	private String location;
	
	public CourtProjection() {}

	public CourtProjection(int courtID, String status, String match, String location) {
		super();
		this.courtID = courtID;
		this.setStatus(status);
		this.match = match;
		this.location = location;
	}
	
	public int getCourtID() {
		return courtID;
	}

	public void setCourtID(int courtID) {
		this.courtID = courtID;
	}

	public String getMatch() {
		return match;
	}

	public void setMatch(String match) {
		this.match = match;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

}

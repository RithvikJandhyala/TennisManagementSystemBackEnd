package com.tennis.springboots.model;
public class School {
	private int schoolID;
	private String name;
	private String address;
	private double ranking;

	public School(int schoolID, String name, String address, double ranking) {
		super();
		this.schoolID = schoolID;
		this.name = name;
		this.address = address;
		this.ranking = ranking;
	}

	public int getSchoolID() {
		return schoolID;
	}

	public void setSchoolID(int schoolID) {
		this.schoolID = schoolID;
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

	public double getRanking() {
		return ranking;
	}

	public void setRanking(double ranking) {
		this.ranking = ranking;
	}

}

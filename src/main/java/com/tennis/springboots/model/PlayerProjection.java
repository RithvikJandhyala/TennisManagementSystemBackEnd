
package com.tennis.springboots.model;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

public class PlayerProjection {
	private int playerID;
	
	private String name;
	private String grade;
	private double ranking;
	private String school;
	private String gender;
	private String status;
 	private String currentMatch;
	public PlayerProjection() {}
	public PlayerProjection(int playerID, String name, String grade, double ranking, String school, String gender,
			String status, String currentMatch) {
		super();
		this.playerID = playerID;
		this.name = name;
		this.grade = grade;
		this.ranking = ranking;
		this.school = school;
		this.gender = gender;
		this.status = status;
		this.currentMatch = currentMatch;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public double getRanking() {
		return ranking;
	}
	public void setRanking(double ranking) {
		this.ranking = ranking;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCurrentMatch() {
		return currentMatch;
	}
	public void setCurrentMatch(String currentMatch) {
		this.currentMatch = currentMatch;
	}
	
}

	

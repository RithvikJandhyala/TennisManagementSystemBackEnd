
package com.tennis.springboots.model;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "player")
public class Player {
	@Id
	private int playerID;
	private String name;
	private String address;
	private String grade;
	@Column(nullable = true)
	private double ranking;
	private String school;
	private String gender;
	//@OneToMany
	private ArrayList<Match> matches = new ArrayList<Match>() ;
	private String status = "Not Checked-In";
	@OneToOne
 	private Match currentMatch;
	public Player() {}
	public Player(int playerID, String name, String address, String grade, double ranking, String school, String gender) {
		super();
		this.playerID = playerID;
		this.name = name;
		this.address = address;
		this.grade = grade;
		this.ranking = ranking;
		this.school = school;
		this.gender = gender;		
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	
	public void assignMatch(Match match) {
		matches.add(match);	
	}
	
	public Match getCurrentMatch() {
		return currentMatch;
	}
	public void setCurrentMatch(Match match) {
		this.currentMatch = match;
	}

	public ArrayList<Match> getMatches()
	{
		return matches;
	}
 
	
	public void setStatus(String status) {
		this.status = status;			
	}
	public String getStatus() {		
		return status;
	}
	
	public String toString() {
		return "Player:" + name + ", Status:  "+ status + ", Match:<<" + currentMatch +">>";
	}	

}

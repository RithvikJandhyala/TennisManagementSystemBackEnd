package com.tennis.springboots.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "doublespair")
public class DoublesPair {
	@Id
	private int ID;
	@OneToOne
	@JoinColumn(name="player1", referencedColumnName="playerID")
	private Player player1;
	@OneToOne
	@JoinColumn(name="player2", referencedColumnName="playerID")
	private Player player2;
	private String school;
	private String type;
	private ArrayList<Match> matches = new ArrayList<Match>() ;
	
	public DoublesPair() {}
	public DoublesPair(int ID, Player player1, Player player2, String school, String type) {
		super();
		this.ID = ID;
		this.player1 = player1;
		this.player2 = player2;
		this.school = school;
		this.type = type;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Player getPlayer1() {
		return player1;
	}
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	
	public void setCurrentMatch(Match match) {
		player1.setCurrentMatch(match);
		player2.setCurrentMatch(match);
	}	
	
	public void assignMatch(Match match) {
		matches.add(match);	
		player1.assignMatch(match);
		player2.assignMatch(match);
	}
	
	public void setStatus(String status) {
		player1.setStatus(status);
		player2.setStatus(status);
		
	}
	
	public String getStatus() {
		if( player1.getStatus().equals("Available") && 
			    player2.getStatus().equals("Available"))
	     		return "Available";
			else return player1.getStatus() +"|"+ player2.getStatus();
	}
	public String getName()
	{
		return player1.getName() + "|"+ player2.getName();		
	}
}

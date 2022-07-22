package com.tennis.springboots.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "match")
public class Match {
	@Id
	private int matchID;
	private String status = "Paired";
	private String matchType;
	private Date datetime;
    @Column(nullable = true)
	private Integer player1ID;
    @Column(nullable = true)
	private Integer player2ID;
	@OneToOne
	@JoinColumn(name="courtid", nullable=true)
	private Court court;
	@Column(nullable = true)
	private int score1;
	@Column(nullable = true)
	private int score2;
	private String gameSet;
	
	public Match() {}
	public Match(int matchID, String status, String matchType, Date datetime, Court court, int player1ID, int player2ID, int score1, int score2, String gameSet) {
		super();
		this.matchID = matchID;
		this.matchType = matchType;
		this.datetime = datetime;
		this.setPlayer1ID(player1ID);
		this.setPlayer2ID(player2ID);
		this.setStatus(status);
		this.score1 = score1;
		this.score2 = score2;
		this.gameSet = gameSet;
		// this should be last because match details are set for the court
		this.setCourt(court);
		
	}
	
	public int getMatchID() {
		return matchID;
	}
	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public Court getCourt() {
		return court;
	}
	public void setCourt(Court court) {
		this.court = court;
		if(court != null)
		{
			court.setCurrentMatch(this);
		}
	}
	public int getPlayer1ID() {
		return player1ID;
	}
	public void setPlayer1ID(int player1ID) {
		this.player1ID = player1ID;
		PlayersList.assignMatch(player1ID,this);
	}
	public int getPlayer2ID() {
		return player2ID;
	}
	public void setPlayer2ID(int player2ID) {
		this.player2ID = player2ID;
		PlayersList.assignMatch(player2ID,this);
	}
	public String getPlayer1Name() {
		return PlayersList.getName(player1ID);
	}
	public String getPlayer2Name() {
		return PlayersList.getName(player2ID);
	}
	public String getGameSet() {
		return gameSet;
	}
	public void setGameSet(String gameSet) {
		this.gameSet = gameSet;
	}	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {		
		this.status = status;
	}
	
	public String getWinner() {
		if(score1 > score2)
			return PlayersList.getName(player1ID);
		else
			return PlayersList.getName(player2ID);
	}
	
	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}
	
	public boolean checkPlayersAvailable()
	{
		return (PlayersList.getStatus(player1ID).equals("Available") ) && (PlayersList.getStatus(player2ID).equals("Available"));		
	}
	
	public String toString() {
		return "Match:" + matchID + ", " +  status + ", " + matchType +
				", Court:" + court + ", Player1:" + PlayersList.getName(player1ID) + " score:"+score1 +
				", Player2:" + PlayersList.getName(player2ID) + " score:"+score2 + ", GameSet:" + gameSet;
	}
	

}

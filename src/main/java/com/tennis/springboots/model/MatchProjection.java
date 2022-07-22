package com.tennis.springboots.model;
import java.util.Date;
public class MatchProjection {
	
	private int matchID;
	private String status;
	private String matchType;
	private Date datetime;


	private int court;
	private String player1Name;
	private String player2Name;
	
	private int player1Score;
	private int player2Score;
	
	private String player1Status;
	private String player2Status;
	
	protected String gameSet;
	
	public MatchProjection() {}
	
	public MatchProjection(int matchID, String status, String matchType, Date datetime, int court, String player1Name,
			String player2Name, int player1Score, int player2Score, String player1Status, String player2Status,String gameSet) {
		super();
		this.matchID = matchID;
		this.status = status;
		this.matchType = matchType;
		this.datetime = datetime;
		this.court = court;
		this.player1Name = player1Name;
		this.player2Name = player2Name;
		this.player1Score = player1Score;
		this.player2Score = player2Score;
		this.setPlayer1Status(player1Status);
		this.setPlayer2Status(player2Status);
		this.gameSet = gameSet;
	}

	public int getMatchID() {
		return matchID;
	}
	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public int getCourt() {
		return court;
	}
	public void setCourt(int court) {
		this.court = court;
	}
	public String getPlayer1Name() {
		return player1Name;
	}
	public void setPlayer1Name(String player1Name) {
		this.player1Name = player1Name;
	}
	public String getPlayer2Name() {
		return player2Name;
	}
	public void setPlayer2Name(String player2Name) {
		this.player2Name = player2Name;
	}
	public int getPlayer1Score() {
		return player1Score;
	}
	public void setPlayer1Score(int player1Score) {
		this.player1Score = player1Score;
	}
	public int getPlayer2Score() {
		return player2Score;
	}
	public void setPlayer2Score(int player2Score) {
		this.player2Score = player2Score;
	}
	public String getGameSet() {
		return gameSet;
	}
	public void setGameSet(String gameSet) {
		this.gameSet = gameSet;
	}

	public String getPlayer1Status() {
		return player1Status;
	}

	public void setPlayer1Status(String player1Status) {
		this.player1Status = player1Status;
	}

	public String getPlayer2Status() {
		return player2Status;
	}

	public void setPlayer2Status(String player2Status) {
		this.player2Status = player2Status;
	}	
	

}

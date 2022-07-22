package com.tennis.springboots.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TennisMain {

	public static ArrayList<Player> players = new ArrayList<Player>() ;
	public static HashMap<Integer, Object> playersList = new HashMap<Integer, Object>();
	public static ArrayList<Match> matches = new ArrayList<Match>() ;
	public static ArrayList<Court> courts = new ArrayList<Court>() ;
	public static void main(String[] args) {
		
		Court c1=  new Court(); c1.setCourtid(1); c1.setLocation("Phoenix Tennis Center");
		Court c2 = new Court(); c2.setCourtid(2); c1.setLocation("Phoenix Tennis Center");
		Court c3 = new Court(); c3.setCourtid(3); c1.setLocation("Phoenix Tennis Center");
		courts.add(c1); courts.add(c2); courts.add(c3);
		
		//Player(int playerID, String name, String address, String grade, double ranking, School school, char gender)
		Player sc1 = new Player(1,"SC1","Sonoran Heights, Scottsdale", "FRESHMAN", 0.0, "Basis Scottsdale", "M");
		Player sc2 = new Player(2,"SC2","Thunderbird, Scottsdale", "FRESHMAN", 0.0, "Basis Scottsdale", "M");
		Player sc3 = new Player(3,"SC3","Mountain View, Scottsdale", "JUNIOR", 0.0, "Basis Scottsdale", "M");
		Player sc4 = new Player(4,"SC4","FLW, Scottsdale", "JUNIOR", 0.0, "Basis Scottsdale", "M");
		
		Player ch1 = new Player(5,"CH1","Address1, Chandler", "FRESHMAN", 0.0, "Basis Chandler", "M");
		Player ch2 = new Player(6,"CH2","Address2, Chandler", "FRESHMAN", 0.0, "Basis Chandler", "M");
		Player ch3 = new Player(7,"CH3","Address3 , Chandler", "JUNIOR", 0.0, "Basis Chandler", "M");
		Player ch4 = new Player(8,"CH4","Address4 , Chandler", "JUNIOR", 0.0, "Basis Chandler", "M");
		players.add(sc1);	players.add(sc2);players.add(sc3);players.add(sc4); 
		players.add(ch1);players.add(ch2);	players.add(ch3);players.add(ch4);			
		for(int i= 0 ; i< players.size(); i++)
		{
			playersList.put(players.get(i).getPlayerID(),players.get(i));
		}
		//DoublesPair(int iD, Player player1, Player player2, Player school, String type) {
		DoublesPair sc1sc4 = new DoublesPair(101,sc1,sc4,"Basis Scottsdale","BB") ;
		DoublesPair ch2ch4 = new DoublesPair(102,ch2,ch4,"Basis Chandler","BB") ;
		playersList.put(sc1sc4.getID(), sc1sc4);
		playersList.put(ch2ch4.getID(), ch2ch4);
	
		PlayersList.playersList = playersList;
				
	    Match smatch1 = new Match(1,"Playing", "", new Date(), c1,1,5,0,0,"PRO GAMESET 8" );
		Match smatch2 = new Match(2,"Playing","", new Date(), c2,2,6,0,0,"PRO GAMESET 8" );
		Match smatch3 = new Match(3,"Playing","", new Date(), c3,3,7,0,0,"PRO GAMESET 8" );
		
		Match dmatch101 = new Match(4,"Paired","", new Date(), null,101,102,0,0,"PRO GAMESET 8");
		
		Match smatch4 = new Match(5, "Paired", "",new Date(), null,1,6,0,0,"PRO GAMESET 8" );
		Match smatch5 = new Match(6, "Paired","", new Date(), null,2,7,0,0,"PRO GAMESET 8" );
		Match smatch6 = new Match(7, "Paired","", new Date(), null,3,5,0,0,"PRO GAMESET 8" );
		
		Match smatch7 = new Match(8, "Paired","", new Date(), null,1,7,0,0,"PRO GAMESET 8" );
		Match smatch8 = new Match(9, "Paired", "",new Date(), null,2,5,0,0,"PRO GAMESET 8" );
		Match smatch9 = new Match(10, "Paired","", new Date(), null,3,6,0,0,"PRO GAMESET 8" );
		
		Match smatch10 = new Match(11, "Paired","", new Date(), null,4,8,0,0,"PRO GAMESET 8" );
		
		//Match states:  Paired, On Deck, Playing, Completed, Cancelled
		matches.add(smatch1);matches.add(smatch2);matches.add(smatch3);
		matches.add(dmatch101);
		matches.add(smatch4);matches.add(smatch5);
		matches.add(smatch6);matches.add(smatch7);matches.add(smatch8); matches.add(smatch9);matches.add(smatch10);
		
		//Account for no-show players
		findAllMatches();
		setPlayerNoShow("SC3");
		
		
		System.out.println("\nScene1 -Status Before Matches Start");
		System.out.println("-------------------------------------------------");
		getMatchAssignments();
		getCourtsStatus();
		findAllPlayersStatus();		
		
		System.out.println("\nScene2 - Match 1 Completed");
		System.out.println("-------------------------------------------------");
		setMatchCompletion(smatch1,8,5);
		
		getMatchAssignments();
		getCourtsStatus();
		findAllPlayersStatus();		
		
		System.out.println("\nScene3 - Match 2,3 Completed");
		System.out.println("-------------------------------------------------");
		setMatchCompletion(smatch2,5,8);
		//setMatchCompletion(smatch3,8,2);
		
		getMatchAssignments();
		getCourtsStatus();
		findAllPlayersStatus();		
		
		System.out.println("\nScene4 - Match 10 Completed");
		System.out.println("-------------------------------------------------");
		setMatchCompletion(smatch10,7,8);
		
		getMatchAssignments();
		getCourtsStatus();
		findAllPlayersStatus();		
		
		System.out.println("\nScene5 - Match 4 Completed");
		System.out.println("-------------------------------------------------");
		setMatchCompletion(smatch4,8,0);
		
		getMatchAssignments();
		getCourtsStatus();
		findAllPlayersStatus();		
		
		System.out.println("\nScene6 - Match 8 Completed");
		System.out.println("-------------------------------------------------");
		setMatchCompletion(smatch8,8,4);
		
		getMatchAssignments();
		getCourtsStatus();
		findAllPlayersStatus();		
		
	    findAllMatches();		
	    findPlayerMatches("SC1");			
	    findPlayerMatches("SC4");			
	    findPlayerStatus("SC3");
					
	}
	
	public static void findPlayerMatches(String playerName)
	{
		System.out.println("Matches Played by " + playerName );
		Player player = findPlayer(playerName);
		for(int i =0; i < player.getMatches().size(); i++)
		    System.out.println((Match)player.getMatches().get(i));	
	}
	
	public static Player findPlayer(String playerName)
	{
		for(int i = 0; i < players.size(); i++) {			
		    Player player = (Player)players.get(i);
			if(player.getName() == playerName) {
				return player;
			}
		}
		return null;
	}
	
	
	public static void setPlayerNoShow(String playerName)
	{
		Player player = findPlayer(playerName);
		player.setStatus("No Show");
	}
	public static void findAllPlayersStatus()
	{
		System.out.println("\nStatus of All Players");
		for(int i = 0; i < players.size(); i++) {			
		    Player player = (Player)players.get(i);
		    System.out.println(player);
		}
	}
	
	public static void findPlayerStatus(String playerName) {
		Player player = findPlayer(playerName);
		System.out.println(player);
	}
	
	//Match states:  Paired, On Deck, Playing, Completed, Cancelled
	
	public static void getMatchAssignments(){
		System.out.println("\nFuture Matches (Playing/On Deck): ");
		for(int i =0; i < matches.size(); i++)
		{			
		    Match match = (Match)matches.get(i);
	        // if Match status is paired and players are available, set the match status to On Deck
		    if(match.getStatus() == "Not Started" && match.checkPlayersAvailable() && match.getCourt() != null)
			{
				match.setStatus("Playing");	
				System.out.println("<<Match :" + match.getMatchID() + " set to On Playing>>");
		    }
			if(match.getStatus() == "Paired" && match.checkPlayersAvailable())
			{
				match.setStatus("On Deck");	
				System.out.println("<<Match :" + match.getMatchID() + " set to On Deck>>");
		    }
			
   
			// if match is On Deck - Try to allocate a court
			if(match.getStatus() == "On Deck")
			{
				// search for available courts
				for(int j=0;j<courts.size();j++)
				{
					if(courts.get(j).getStatus() == "Available")
					{   // allocate court to match
						System.out.println("<<Match :" + match.getMatchID() + " assigned Court:" + courts.get(j).getCourtid() + " >>");
						match.setCourt(courts.get(j));
						match.setStatus("Playing"); 
						break;
					}
				}	
			}
			if(match.getStatus() == "On Deck" || match.getStatus() == "Playing")
				System.out.println(match);
		}
		
	}	

	public static void setMatchCompletion(Match match, int score1, int score2)
	{
		match.setStatus("Completed");
		match.setScore1(score1);
		match.setScore2(score2);
		//System.out.println("Match: "+ match.getMatchID() + " Completed with scores: Player1-" + player1Score + ", Player2-" + player2Score);
		System.out.println(match);
	}
	
	public static void findAllMatches() {
		for(int i =0; i < matches.size(); i++)
		    System.out.println((Match)matches.get(i));		   
	}
	public static void getCourtsStatus()
	{
		for(int i =0; i < courts.size(); i++)
		    System.out.println((Court)courts.get(i));
	}
	

}

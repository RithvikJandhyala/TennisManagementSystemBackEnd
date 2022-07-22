package com.tennis.springboots.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.tennis.springboots.model.DoublesPair;
import com.tennis.springboots.model.Match;
import com.tennis.springboots.model.MatchProjection;
import com.tennis.springboots.model.PlayerProjection;
import com.tennis.springboots.model.Player;
import com.tennis.springboots.model.PlayersList;
import com.tennis.springboots.repository.DoublesPairRepository;
import com.tennis.springboots.repository.PlayersRepository;

@Service
public class PlayersService {

	@Autowired
	private PlayersRepository playersRepository;
	@Autowired
	private DoublesPairRepository doublesPairRepository;
	@Lazy @Autowired
	private MatchesAssignmentService matchesAssignmentService;
	
	
	public void save(Player p) {
		this.playersRepository.save(p);
	}
	
	public void save(DoublesPair p) {
		this.doublesPairRepository.save(p);
	}
	
	public Player findById(int id)
	{
		return playersRepository.findById(id).get();
	}
	
	public PlayerProjection addPlayer(PlayerProjection playerProj)
	{
		//check if this is existing or new match
        System.out.println(playerProj.getPlayerID());
        System.out.println("Finding if Player exists:" + playersRepository.findById(playerProj.getPlayerID()));
		Player player;
		if(!playersRepository.findById(playerProj.getPlayerID()).isPresent())
		{
			System.out.println("New Player created");
			player = new Player();
			player.setPlayerID(playerProj.getPlayerID());
			player.setName(playerProj.getName());
			player.setGrade(playerProj.getGrade());
			player.setRanking(playerProj.getRanking());
			player.setSchool(playerProj.getSchool());
			player.setGender(playerProj.getGender());
			player.setStatus(playerProj.getStatus());	
			playersRepository.save(player);
		}
		else {
			setStatus(playerProj.getPlayerID(), playerProj.getStatus());			
		}
		matchesAssignmentService.updateMatchAssignments();
		return playerProj;
	}

	public void save(int playerID) {
		Object player = PlayersList.get(playerID);
		if(player != null)
			save((Player)player);
	}
	
	public void setStatus(Player p, String status)
	{
		
	}
	
	public HashMap<Integer, Object> getSinglesAndDoublesPlayers()
	{
		HashMap<Integer, Object> playersList = new HashMap<Integer, Object>();
		//get All Singles Players
		List<Player> players = this.playersRepository.findAll();
		for(int i = 0; i< players.size();i++) {			
			playersList.put(players.get(i).getPlayerID(),players.get(i));
		}
		//get ALl Doubles Players
		List<DoublesPair> doublesPair = this.doublesPairRepository.findAll();
		for(int i = 0; i< doublesPair.size();i++) {			
			playersList.put(doublesPair.get(i).getID(),doublesPair.get(i));
		}
		return playersList;
	}
	
	
	public Player findPlayerByName(String name) {
		List<Player> players = this.playersRepository.findAll();
		for(int i = 0; i< players.size();i++) {
			if(players.get(i).getName().equals(name))
				return players.get(i);
		}
		return new Player();
	}

	public int findPlayerIDByName(String playerName) {
		return findPlayerByName(playerName).getPlayerID();
	}

	public String getStatus(int playerid) {
		String status ="";
		if(playersRepository.findById(playerid).isPresent())
		{
			status = playersRepository.getById(playerid).getStatus();	
		}
		else if(doublesPairRepository.findById(playerid).isPresent())
		{
			status = doublesPairRepository.getById(playerid).getStatus();
		}
		return status;
	}

	public String getStatusforUI(int playerid) {
		String status = getStatus(playerid) ;
		if(status.equals("Playing") || status.equals("OnDeck") )
			status = status + "/Match:" + playersRepository.getById(playerid).getCurrentMatch().getMatchID();
		return status;
	}
	public void setStatus(int playerid, String status) {		
		if(playersRepository.findById(playerid).isPresent())
		{
			Player p = playersRepository.getById(playerid);
			p.setStatus(status);
			playersRepository.save(p);
		}	
		else if(doublesPairRepository.findById(playerid).isPresent())
		{
			DoublesPair dp = doublesPairRepository.getById(playerid);
			dp.setStatus(status);
			doublesPairRepository.save(dp);
		}
	}

	
	public void setCurrentMatch(int playerid, Match m) {
		if(playersRepository.findById(playerid).isPresent())
		{
			playersRepository.getById(playerid).setCurrentMatch(m);	
		}
		else if(doublesPairRepository.findById(playerid).isPresent())
		{
			doublesPairRepository.getById(playerid).setCurrentMatch(m);	
		}
		
	}
	
	public List<PlayerProjection> getPlayers(){
		List<PlayerProjection> playerProjection = new ArrayList<PlayerProjection>();
		List<Player> players= playersRepository.findAll();		
		for(int i = 0; i< players.size();i++) {
			
			Player player = players.get(i);
			Match currentMatch = player.getCurrentMatch();
			int court = 0;	
			String opponent = "", matchDetails = "";
			if(currentMatch != null)
			{
				if(currentMatch.getPlayer1ID() == player.getPlayerID())
					opponent = currentMatch.getPlayer2Name();
				else
					opponent = currentMatch.getPlayer1Name();
				if(currentMatch.getCourt() != null)
					court = currentMatch.getCourt().getCourtid();
				matchDetails = currentMatch.getMatchID()+"| Opp:" +opponent+ " |Court:" + court;
			}
			playerProjection.add(new PlayerProjection(player.getPlayerID(),
								player.getName(),
								player.getGrade(),
								player.getRanking(),
								player.getSchool(),
								player.getGender(),
								player.getStatus(),
								matchDetails));
		}
		return playerProjection;
	}
}
		




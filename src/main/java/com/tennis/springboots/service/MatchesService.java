package com.tennis.springboots.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tennis.springboots.model.Court;
import com.tennis.springboots.model.Match;
import com.tennis.springboots.model.MatchProjection;
import com.tennis.springboots.repository.MatchesRepository;

@Service
public class MatchesService {

	@Autowired
	private MatchesRepository matchesRepository;
	@Autowired
	private MatchesAssignmentService matchesAssignmentService;
	@Autowired
	private PlayersService playersService;
	@Autowired
	private CourtsService courtsService;
	
	public void save(Match m) {
		this.matchesRepository.save(m);
		courtsService.save(m.getCourt());
		//playersService.save(m.getPlayer1ID());
	}
	
	public void update(Match m) {
		playersService.save(m.getPlayer1ID());
		playersService.save(m.getPlayer2ID());
		courtsService.setCurrentMatch(m.getCourt(),m);
	}
	
	public Match findById(int id)
	{
		return matchesRepository.findById(id).get();
	}

	public List<MatchProjection> getMatches(){
		List<MatchProjection> matchesProjection = new ArrayList<MatchProjection>();
		List<Match> matches= matchesRepository.findAll();
		for(int i = 0; i< matches.size();i++) {
			Match match = matches.get(i);
			int courtID = 0;
			if(match.getCourt() != null) courtID = match.getCourt().getCourtid();
			matchesProjection.add(new MatchProjection(match.getMatchID(),
													  match.getStatus(),
													  match.getMatchType(),
													  match.getDatetime(),
													  courtID,
													  match.getPlayer1Name(),
													  match.getPlayer2Name(),
													  match.getScore1(),
													  match.getScore2(),
													  playersService.getStatusforUI(match.getPlayer1ID()),
													  playersService.getStatusforUI(match.getPlayer2ID()),
													  match.getGameSet()));										  
		}
		return matchesProjection;
	}
	
	public MatchProjection addMatch(MatchProjection matchProj)
	{
		//check if this is existing or new match
        System.out.println("Match ID: "+ matchProj.getMatchID()+" Player1:"+matchProj.getPlayer1Name()+" Player1 Score:"+matchProj.getPlayer1Score()+
        		           " Player2:"+matchProj.getPlayer2Name()+" Player2 Score:"+matchProj.getPlayer2Score());
        System.out.println("Finding if match exists:" + matchesRepository.findById(matchProj.getMatchID()));
		Match match;
		if(!matchesRepository.findById(matchProj.getMatchID()).isPresent())
		{
			System.out.println("New match created");
			match = new Match();
			match.setMatchID(matchProj.getMatchID());
			match.setStatus(matchProj.getStatus());						 
			match.setMatchType(matchProj.getMatchType());					
			match.setDatetime(matchProj.getDatetime());				     
			//match.setCourt(courtsService.findById(matchProj.getCourt()));						
			match.setPlayer1ID(playersService.findPlayerIDByName(matchProj.getPlayer1Name()));				 
			match.setPlayer2ID(playersService.findPlayerIDByName(matchProj.getPlayer2Name()));					 
		    match.setScore1(matchProj.getPlayer1Score());				 
		    match.setScore2(matchProj.getPlayer2Score());				 
			match.setGameSet(matchProj.getGameSet());						 
		}
		else {
			match = matchesRepository.findById(matchProj.getMatchID()).get();
			// update only when there is status change
			if(!match.getStatus().equals(matchProj.getStatus())) {
				matchesAssignmentService.setStatus(match,matchProj.getStatus());
			}
			
			match.setScore1(matchProj.getPlayer1Score());
			match.setScore2(matchProj.getPlayer2Score());			
		}
		matchesRepository.save(match);
		matchesAssignmentService.updateMatchAssignments();
		return matchProj;
	}
}
	
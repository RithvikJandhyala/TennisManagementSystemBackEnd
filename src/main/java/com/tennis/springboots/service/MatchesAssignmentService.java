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
public class MatchesAssignmentService {

	@Autowired
	private MatchesRepository matchesRepository;
	@Autowired
	private PlayersService playersService;
	@Autowired
	private CourtsService courtsService;
	
	
	public void updateMatchAssignments(){
		System.out.println("\nFuture Matches (Playing/On Deck): ");
		List<Match> matches= matchesRepository.findAll();
		for(int i =0; i < matches.size(); i++)
		{			
		    Match match = (Match)matches.get(i);
	        if(match.getStatus() == "Not Started" && checkPlayersAvailable(match) && match.getCourt() != null)
			{
				setStatus(match,"Playing");	
				System.out.println("<<Match :" + match.getMatchID() + " set to On Playing>>");
		    }
		    if(match.getStatus() != "Cancelled" && checkNoShow(match))
			{
				setStatus(match,"Cancelled");	
				System.out.println("<<Match :" + match.getMatchID() + " set to Cancelled>>");
		    }
		    // if Match status is paired and players are available, set the match status to On Deck
			if(match.getStatus().equals("Paired") && checkPlayersAvailable(match))
			{
				setStatus(match,"OnDeck");	
				System.out.println("<<Match :" + match.getMatchID() + " set to On Deck>>");
		    }
			
   
			// if match is On Deck - Try to allocate a court
			if(match.getStatus().equals("OnDeck"))
			{
				//get all courts for a location
				List<Court> courts =  courtsService.findAll();
				// search for available courts
				for(int j=0;j<courts.size();j++)
				{
					if(courts.get(j).getStatus().equals("Available"))
					{   // allocate court to match
						System.out.println("<<Match :" + match.getMatchID() + " assigned Court:" + courts.get(j).getCourtid() + " >>");
						match.setCourt(courts.get(j));
						setStatus(match,"Playing"); 
						break;
					}
				}				
			}
			if(match.getStatus().equals("On Deck") || match.getStatus().equals("Playing"))
				System.out.println(match);
		}
		matchesRepository.saveAll(matches);
		
	}	
	
	public boolean checkPlayersAvailable(Match m)
	{
		return (playersService.getStatus(m.getPlayer1ID()).equals("Available") ) && (playersService.getStatus(m.getPlayer2ID()).equals("Available"));		
	}
	
	public boolean checkNoShow(Match m)
	{
		return (playersService.getStatus(m.getPlayer1ID()).contains("NoShow") ) || (playersService.getStatus(m.getPlayer2ID()).contains("NoShow"));		
	}
	
	public void setStatus(Match m, String status)
	{
		m.setStatus(status);
		if(status.equals("OnDeck") || status.equals("Playing"))
		{
			playersService.setStatus(m.getPlayer1ID(),status);
			playersService.setCurrentMatch(m.getPlayer1ID(),m);
			playersService.setStatus(m.getPlayer2ID(),status);
			playersService.setCurrentMatch(m.getPlayer2ID(),m);
			if(!status.equals("OnDeck") ) 
				courtsService.setCurrentMatch(m.getCourt(),m);
		}
		
		if(status.equals("Completed"))
		{
			playersService.setStatus(m.getPlayer1ID(),"Available");
			playersService.setCurrentMatch(m.getPlayer1ID(),null);
			playersService.setStatus(m.getPlayer2ID(),"Available");
			playersService.setCurrentMatch(m.getPlayer2ID(),null);
			courtsService.setCurrentMatch(m.getCourt(),null);
		}
		if(status.equals("Cancelled"))
		{
			if(m.getCourt() != null )
			{
			    courtsService.setCurrentMatch(m.getCourt(),null);
			    m.setCourt(null);
			}
		}
		
	}

}

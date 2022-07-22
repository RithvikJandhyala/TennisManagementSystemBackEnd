package com.tennis.springboots.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tennis.springboots.model.Court;
import com.tennis.springboots.model.CourtProjection;
import com.tennis.springboots.model.Match;
import com.tennis.springboots.model.MatchProjection;
import com.tennis.springboots.repository.CourtsRepository;



@Service
public class CourtsService {

	@Autowired
	private CourtsRepository courtsRepository;
	public void save(Court court) {
		if(court != null)
		  this.courtsRepository.save(court);
	}
	
	public Court findById(Integer id)
	{
		return courtsRepository.findById(id).get();
	}
	
	public List<Court> findAll() {
		return courtsRepository.findAll();
	}
	
	public List<CourtProjection> getCourts()
	{
		//updateMatchAssignments();
		List<CourtProjection> courtsProjection = new ArrayList<CourtProjection>();
		List<Court> courts= courtsRepository.findAll();
		for(int i = 0; i< courts.size();i++) {
			Court court = courts.get(i);
			Match match = court.getCurrentMatch();
			String matchStr = "";
			if(match != null) {				
				matchStr = match.getMatchID()+"-"+match.getPlayer1Name()+"|"+match.getPlayer2Name();
			}
			courtsProjection.add(new CourtProjection(court.getCourtid(),
													court.getStatus(),
													matchStr,
													court.getLocation()));										  
		}
		return courtsProjection;
	}

	public void setCurrentMatch(Court c, Match m) {
		if(c != null)
		{
			c.setCurrentMatch(m);
			courtsRepository.save(c);			
		}
	}
	
}

package com.tennis.springboots.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tennis.springboots.model.Match;

@Repository
public interface MatchesRepository extends JpaRepository<Match, Integer>{
	

}

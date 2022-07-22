package com.tennis.springboots.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tennis.springboots.model.Player;


@Repository
public interface PlayersRepository extends JpaRepository<Player, Integer>{
	
	List<Player> findBySchool(String school);
	List<Player> findByName(String name);
	List<Player> findByGrade(String grade);
	List<Player> findByGender(char gender);
	List<Player> findByRanking(double ranking);

}

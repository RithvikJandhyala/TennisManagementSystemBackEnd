package com.tennis.springboots.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tennis.springboots.model.Court;


@Repository
public interface CourtsRepository extends JpaRepository<Court, Integer>{
	
}

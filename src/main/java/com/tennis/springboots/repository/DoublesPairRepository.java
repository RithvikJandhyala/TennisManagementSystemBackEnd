package com.tennis.springboots.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tennis.springboots.model.DoublesPair;


@Repository
public interface DoublesPairRepository extends JpaRepository<DoublesPair, Integer>{
	
}

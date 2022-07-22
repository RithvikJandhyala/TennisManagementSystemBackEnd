package com.tennis.springboots.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tennis.springboots.model.TennisCenter;


@Repository
public interface TennisCenterRepository extends JpaRepository<TennisCenter, String>{
	
}

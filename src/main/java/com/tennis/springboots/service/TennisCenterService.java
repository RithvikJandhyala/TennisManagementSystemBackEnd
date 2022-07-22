package com.tennis.springboots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tennis.springboots.model.TennisCenter;
import com.tennis.springboots.repository.TennisCenterRepository;


@Service
public class TennisCenterService {

	@Autowired
	private TennisCenterRepository tennisCenterRepository;
	public void save(TennisCenter tc) {
		this.tennisCenterRepository.save(tc);
	}
	
	public TennisCenter findById(String id)
	{
		return tennisCenterRepository.findById(id).get();
	}

}

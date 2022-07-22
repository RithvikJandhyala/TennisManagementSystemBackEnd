package com.tennis.springboots;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tennis.springboots.model.Court;
import com.tennis.springboots.model.DoublesPair;
import com.tennis.springboots.model.Match;
import com.tennis.springboots.model.Player;
import com.tennis.springboots.model.PlayersList;
import com.tennis.springboots.service.CourtsService;
import com.tennis.springboots.service.MatchesService;

@SpringBootApplication
public class TennisBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TennisBackendApplication.class, args);
	}

	@Autowired
	private CourtsService cs;
	@Autowired
	private MatchesService ms;
	
	@Autowired
	private com.tennis.springboots.service.PlayersService ps;


	@Override
	public void run(String... args) throws Exception {
		Court c1=  new Court(); c1.setCourtid(1); c1.setLocation("Phoenix Tennis Center");
		Court c2 = new Court(); c2.setCourtid(2); c2.setLocation("Phoenix Tennis Center");
		Court c3 = new Court(); c3.setCourtid(3); c3.setLocation("Phoenix Tennis Center");
		this.cs.save(c1);
		this.cs.save(c2);
		this.cs.save(c3);
		
	    this.ps.save(new Player(1, "Scottsdale P1", "Sonoran Heights, Scottsdale", "FRESHMAN", 1, "Basis Scottsdale", "M"));
		this.ps.save(new Player(2, "Scottsdale P2", "Thunderbird, Scottsdale", "FRESHMAN", 3, "Basis Scottsdale", "M"));
		this.ps.save(new Player(3, "Scottsdale P3", "Mountain View, Scottsdale", "JUNIOR", 8, "Basis Scottsdale", "M"));
		this.ps.save(new Player(4, "Scottsdale P4", "FLW, Scottsdale", "JUNIOR", 7, "Basis Scottsdale", "M"));

		this.ps.save(new Player(5, "Chandler P1", "Address1, Chandler", "FRESHMAN", 11, "Basis Chandler", "M"));
		this.ps.save(new Player(6, "Chandler P2", "Address2, Chandler", "FRESHMAN", 12, "Basis Chandler", "M"));
		this.ps.save(new Player(7, "Chandler P3", "Address3 , Chandler", "JUNIOR", 2, "Basis Chandler", "M"));
		this.ps.save(new Player(8, "Chandler P4", "Address4 , Chandler", "JUNIOR", 4, "Basis Chandler", "M"));
		
		this.ps.save(new DoublesPair(101,ps.findById(1),ps.findById(4),"Basis Scottsdale","BB")) ;
		this.ps.save(new DoublesPair(102,ps.findById(6),ps.findById(8),"Basis Chandler","BB")) ;
		
		PlayersList.playersList = ps.getSinglesAndDoublesPlayers();
		
		this.ms.save(new Match(1,"Not Started", "",new Date(), cs.findById(1),1,5,0,0,"PRO GAMESET 8" ));
		this.ms.save(new Match(2,"Not Started", "",new Date(), cs.findById(2),2,6,0,0,"PRO GAMESET 8" ));
		this.ms.save(new Match(3,"Not Started", "",new Date(), cs.findById(3),3,7,0,0,"PRO GAMESET 8" ));
		//Doubles Match
		this.ms.save(new Match(4,"Paired","", new Date(), null,101,102,0,0,"PRO DOUBLES GAMESET 8"));
		this.ms.save(new Match(5, "Paired", "",new Date(),null,4,8,0,0,"PRO GAMESET 8" ));
		this.ms.save(new Match(6, "Paired", "",new Date(),null,1,6,0,0,"PRO GAMESET 8" ));
		this.ms.save(new Match(7, "Paired", "",new Date(),null,2,7,0,0,"PRO GAMESET 8" ));
		this.ms.save(new Match(8, "Paired", "",new Date(),null,3,5,0,0,"PRO GAMESET 8" ));
		this.ms.save(new Match(9, "Paired", "",new Date(),null,1,7,0,0,"PRO GAMESET 8" ));
		this.ms.save(new Match(10, "Paired", "",new Date(),null,2,5,0,0,"PRO GAMESET 8" ));
		this.ms.save(new Match(11, "Paired", "",new Date(),null,3,6,0,0,"PRO GAMESET 8" ));

	
	}
	
}

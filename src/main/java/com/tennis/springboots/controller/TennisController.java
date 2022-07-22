package com.tennis.springboots.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tennis.springboots.model.CourtProjection;
import com.tennis.springboots.model.MatchProjection;
import com.tennis.springboots.model.Player;
import com.tennis.springboots.model.PlayerProjection;
import com.tennis.springboots.repository.PlayersRepository;
import com.tennis.springboots.service.MatchesService;
import com.tennis.springboots.service.PlayersService;
import com.tennis.springboots.service.CourtsService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class TennisController {
	@Autowired
	private PlayersRepository playersRepository;
	@Autowired
	private MatchesService matchesService;
	@Autowired
	private PlayersService playersService;
	@Autowired
	private CourtsService courtsService;
	
	@GetMapping("players")
	public List<PlayerProjection> getPlayers(){
		return this.playersService.getPlayers();
	}
	
	@GetMapping("playercount")
	public long getPlayerCount(){
		return this.playersRepository.count();
	}
	
	@GetMapping("/players/playerid={id}")
	public Optional<Player> getPlayerById(@PathVariable("id") int id) {
		return this.playersRepository.findById(id);
	}
	
	
	@GetMapping("players/school")
	public ResponseEntity<List<Player>> getPlayersBySchool(@RequestParam String school){
		return new ResponseEntity<List<Player>>(playersRepository.findBySchool(school),HttpStatus.OK);
		
	}
	
	@PostMapping("players/add")
	public List<Player> addNewPlayers(@RequestBody List<Player> players){
		return playersRepository.saveAll(players);		
	}
	
	@PostMapping("players")
	public PlayerProjection addPlayer(@RequestBody PlayerProjection playerProj){
		return playersService.addPlayer(playerProj);		
	}
	
	@PutMapping("players/update/{id}")
	public void updatePlayer(@RequestBody Player player, @PathVariable("id") int id) {
		List<Player> players = this.playersRepository.findAll();
		for(int i = 0; i< players.size();i++) {
			if(players.get(i).getPlayerID() == id)
				players.set(i, player);			
		}
	}
	
	@GetMapping("matches")
	public List<MatchProjection> getMatches(){
		return matchesService.getMatches();
	}
	
	/*@GetMapping("players/playerid={id}?setstatus={status}")
	public Optional<Player> getPlayerById(@PathVariable("id") int id,@PathVariable("status") String status) {
		Player p1 = this.playersRepository.findById(id).get();
        p1.setStatus(status);
        System.out.println(p1);
        playersRepository.save(p1);
        return playersRepository.findById(id);
        
	}*/
	@PostMapping("matches")
	public MatchProjection addMatch(@RequestBody MatchProjection match){
		System.out.println("New add request");
		return matchesService.addMatch(match);		
	}

	@GetMapping("courts")
	public List<CourtProjection> getCourts(){
		return courtsService.getCourts();
	}
}

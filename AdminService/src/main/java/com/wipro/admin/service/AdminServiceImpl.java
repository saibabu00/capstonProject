package com.wipro.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.admin.exceptions.PlayerAlreadyExistsException;
import com.wipro.admin.exceptions.PlayerNotFoundException;
import com.wipro.admin.model.Bowler;
import com.wipro.admin.model.Player;
import com.wipro.admin.repository.AdminRepository;
import com.wipro.admin.repository.BowlerRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository playerRepo;
	
	@Autowired
	private BowlerRepository bowlerRepo;
	
	
	
	public AdminServiceImpl(AdminRepository playerRepo) {
		super();
		this.playerRepo = playerRepo;
	}

	@Override
	public List<Player> getAllPlayers() {
		List<Player> players = playerRepo.findAll();
		 return players;
		 
	}
	
	@Override
	public boolean addPlayer(Player player) throws PlayerAlreadyExistsException {
		
		Optional<Player> playerObj = playerRepo.findById(player.getPid());
		if(playerObj.isPresent()) {
			throw new PlayerAlreadyExistsException("Player with id already exists");
		}
		else {
			playerRepo.save(player);
			return true;
		}
	}

	@Override
	public void deletePlayer(int pid) throws PlayerNotFoundException {
		
		Optional<Player> playerObj =playerRepo.findById(pid);
		if(playerObj.isPresent()) {
			playerRepo.deleteById(pid);
		}
		else {
			throw new PlayerNotFoundException("Player with pid not found");
		}
		
	}

	@Override
	public List<Player> searchPlayer(String playerName) throws PlayerNotFoundException {
		List<Player> playerList = playerRepo.findByPlayerNameIgnoreCase(playerName);
		if(playerList == null) {
			throw new PlayerNotFoundException("Searched player not found");
		}
		else {
			return playerList;
		}
	}

	@Override
	public Player UpdatePlayer(int pid, Player player) throws PlayerNotFoundException {
		Player playerObj = playerRepo.findById(pid).get();
		if(playerObj!=null) {
			playerObj.setSpan(player.getSpan());
			playerObj.setMatches(player.getMatches());
			playerObj.setRuns(player.getRuns());
			playerObj.setHundreds(player.getHundreds());
			playerObj.setFifties(player.getFifties());
			playerRepo.save(playerObj);
			return playerObj;
			
		}
		else {
			throw new PlayerNotFoundException("The Player you want to update is not present");
		}

	}

	@Override
	public List<Bowler> getAllBowlers() {
		// TODO Auto-generated method stub
		List<Bowler> bowlerList = bowlerRepo.findAll();
		return bowlerList;
	}

	@Override
	public boolean addBowler(Bowler bowler) throws PlayerAlreadyExistsException {
		Optional<Bowler> bowlerObj = bowlerRepo.findById(bowler.getPbid());
		if(bowlerObj.isPresent()) {
			throw new PlayerAlreadyExistsException("Bowler with id already exists");
		}
		else {
			bowlerRepo.save(bowler);
			return true;
			}
		}

	@Override
	public void deleteBowler(int pbid) throws PlayerNotFoundException {
		Optional<Bowler> bowlerObj =bowlerRepo.findById(pbid);
		if(bowlerObj.isPresent()) {
			bowlerRepo.deleteById(pbid);
		}
		else {
			throw new PlayerNotFoundException("Bowler with pid not found");
		}
	}

	@Override
	public Bowler UpdateBowler(int pbid, Bowler bowler) throws PlayerNotFoundException {
		
		Bowler bowlerObj = bowlerRepo.findById(pbid).get();
		if(bowlerObj!=null) {
			bowlerObj.setSpan(bowler.getSpan());
			bowlerObj.setMatches(bowler.getMatches());
			bowlerObj.setWickets(bowler.getWickets());
			bowlerObj.setFiveWktHaul(bowler.getFiveWktHaul());
			
			bowlerRepo.save(bowlerObj);
			return bowlerObj;
			
		}
		else {
			throw new PlayerNotFoundException("The Player you want to update is not present");
		}
	}

	@Override
	public List<Bowler> searchBowler(String bowlerName) throws PlayerNotFoundException {
		
		List<Bowler> bowlerList = bowlerRepo.findByBowlerNameIgnoreCase(bowlerName);
		if(bowlerList == null) {
			throw new PlayerNotFoundException("Searched player not found");
		}
		else {
			return bowlerList;
		}
	}

	

}

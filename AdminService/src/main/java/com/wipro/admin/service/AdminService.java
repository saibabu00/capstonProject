package com.wipro.admin.service;

import java.util.List;

import com.wipro.admin.exceptions.PlayerAlreadyExistsException;
import com.wipro.admin.exceptions.PlayerNotFoundException;
import com.wipro.admin.model.Bowler;
import com.wipro.admin.model.Player;

public interface AdminService {
	
	public List<Player> getAllPlayers();
	
	public boolean addPlayer(Player player) throws PlayerAlreadyExistsException;
	
	public void deletePlayer(int pid) throws PlayerNotFoundException;
	
	public Player UpdatePlayer(int pid, Player player) throws PlayerNotFoundException;
	
	public List<Player> searchPlayer(String playerName) throws PlayerNotFoundException;
	
	
	
    public List<Bowler> getAllBowlers();
	
	public boolean addBowler(Bowler bowler) throws PlayerAlreadyExistsException;
	
	public void deleteBowler(int pbid) throws PlayerNotFoundException;
	
	public Bowler UpdateBowler(int pbid, Bowler bowler) throws PlayerNotFoundException;
	
	public List<Bowler> searchBowler(String bowlerName) throws PlayerNotFoundException;
	
	
	

}

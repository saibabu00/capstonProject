package com.wipro.admin.service;

import java.util.List;

import com.wipro.admin.exceptions.PlayerAlreadyExistsException;
import com.wipro.admin.exceptions.PlayerNotFoundException;
import com.wipro.admin.model.Player;

public interface AdminService {
	
	public List<Player> getAllPlayers();
	
	public boolean addPlayer(Player player) throws PlayerAlreadyExistsException;
	
	public void deletePlayer(int pid) throws PlayerNotFoundException;
	
	public Player UpdatePlayer(int pid, Player player) throws PlayerNotFoundException;
	
	public List<Player> searchPlayer(String playerName) throws PlayerNotFoundException;

}

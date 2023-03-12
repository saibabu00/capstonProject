package com.wipro.favouriteservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.favouriteservice.exception.FavouritesAreEmptyException;
import com.wipro.favouriteservice.exception.PlayerAlreadyExistsException;
import com.wipro.favouriteservice.exception.PlayerNotFoundException;
import com.wipro.favouriteservice.model.Player;
import com.wipro.favouriteservice.model.User;
import com.wipro.favouriteservice.repository.FavouriteRepository;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    
	@Autowired
	private FavouriteRepository favRepository;
	
//	@Autowired
//	public FavouriteServiceImpl(FavouriteRepository favRepository) {
//		super();
//		this.favRepository = favRepository;
//	}

	@Override
	public User savePlayerToFavorite(Player player, String username) throws PlayerAlreadyExistsException {
		// TODO Auto-generated method stub
		System.out.println("in implementation");
		System.out.println(player+" "+username);
		
		User user1 = favRepository.findByUsername(username);
		System.out.println("user1 is "+user1);
		if (user1 == null) {
			System.out.println("yes user is null");
			user1 = new User(username, new ArrayList<Player>());
		}
		System.out.println("user is not null");
		List<Player> playerList = user1.getPlayerList();
		System.out.println("players list is "+playerList);
		if (playerList != null) {
			for (Player p : playerList) {
				System.out.println("entered for block");
				if (p.getPlayerName().equals(player.getPlayerName())) {
					throw new PlayerAlreadyExistsException();
				}
			}

			playerList.add(player);
			
			System.out.println("Saving Data if block");
			user1.setPlayerList(playerList);
			favRepository.save(user1);
		}

		else {
			playerList = new ArrayList();
			playerList.add(player);

			user1.setPlayerList(playerList);
			favRepository.save(user1);
		}
		return user1;

	}

	@Override
	public User deletePlayerFromFavorite(String playerName , String username) throws PlayerNotFoundException {
		User user1 = favRepository.findByUsername(username);
//		boolean trackFound = false;
		int indexnum = 0;
		List<Player> playerList = user1.getPlayerList();

		if (playerList != null && playerList.size() > 0) {
			for (Player t : playerList) {
				indexnum++;
				if (t.getPlayerName().equals(playerName)) {
					playerList.remove(indexnum - 1);
					user1.setPlayerList(playerList);
					favRepository.save(user1);
					break;
				}
			}

		}

		else {
			throw new PlayerNotFoundException();
		}
		return user1;
	}

	@Override
	public List<Player> getPlayerList(String username) throws FavouritesAreEmptyException{
		 System.out.println("entered get player list method");
		User user1 = favRepository.findByUsername(username);
		 System.out.println("user 1 is list  "+user1);
		if(user1 !=null)
		{
			System.out.println("entered if block");
		return user1.getPlayerList();
		}
		else
		{
			System.out.println("entered catch block");
			throw new FavouritesAreEmptyException();
		}
	}
	


}

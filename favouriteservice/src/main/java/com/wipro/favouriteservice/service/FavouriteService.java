package com.wipro.favouriteservice.service;

import java.util.List;

import com.wipro.favouriteservice.exception.FavouritesAreEmptyException;
import com.wipro.favouriteservice.exception.PlayerAlreadyExistsException;
import com.wipro.favouriteservice.exception.PlayerNotFoundException;
import com.wipro.favouriteservice.model.Player;
import com.wipro.favouriteservice.model.User;




public interface FavouriteService {

	public User savePlayerToFavorite(Player player, String username) throws PlayerAlreadyExistsException;
	public User deletePlayerFromFavorite(String trackId , String username ) throws PlayerNotFoundException;

	public List<Player> getPlayerList(String username ) throws FavouritesAreEmptyException;
	

}

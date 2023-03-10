package com.stackroute.favouriteservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.model.Player;
import com.stackroute.favouriteservice.model.User;
import com.stackroute.favouriteservice.exception.FavouritesAreEmptyException;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.exception.UserAlreadyExistsException;
import com.stackroute.favouriteservice.service.FavouriteService;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/favoriteservice")
public class FavoriteController {

	private ResponseEntity responseEntity;

	private FavouriteService favService;

	public FavoriteController() {
		super();
	}

	@Autowired
	public FavoriteController(FavouriteService favService) {
		super();
		this.favService = favService;
	}

	@PostMapping("/user/{username}/player")
	public ResponseEntity<?> addPlayerToFavoriteList(@RequestBody Player cPlayer,
			@PathVariable String username) throws PlayerAlreadyExistsException {
		System.out.println("called");
		System.out.println(username);
		System.out.println(cPlayer);
		

		try {
			System.out.println(".........................");
			System.out.println("entered try block");
			User user1 = favService.savePlayerToFavorite(cPlayer, username);
			System.out.println(user1);
			responseEntity = new ResponseEntity<User>(user1, HttpStatus.CREATED);
		} catch (PlayerAlreadyExistsException e) {
			System.out.println("entered catch1  block");
			throw new PlayerAlreadyExistsException();
		} catch (Exception e) {
			System.out.println("entered catch2 block");
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}

	@DeleteMapping("/user/{username}/Deleteplayer/{pid}")
	public ResponseEntity<?> deleteFromList(@PathVariable String pid, @PathVariable String username)
			throws PlayerNotFoundException {
		System.out.println("called");
		System.out.println(username);
		System.out.println(pid);
		try {
			User user1 = favService.deletePlayerFromFavorite(pid, username);
			responseEntity = new ResponseEntity<User>(user1, HttpStatus.OK);
		} catch (PlayerNotFoundException e) {
			throw new PlayerNotFoundException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}
	
	@GetMapping("/user/{username}/getplayerList")
	public ResponseEntity<?> getPlayerList(@PathVariable String username) throws PlayerNotFoundException, FavouritesAreEmptyException {
     System.out.println("entered get mapping");
		try {
			 System.out.println("entered try block");
			List<Player> playerList = favService.getPlayerList(username);
		   responseEntity = new ResponseEntity(playerList, HttpStatus.OK);
			
		}
		catch(FavouritesAreEmptyException e)
		{
			 System.out.println("entered catch1  block");
			throw new FavouritesAreEmptyException();
		}
		catch (Exception e) {
			 System.out.println("entered catch2  block");
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}


}

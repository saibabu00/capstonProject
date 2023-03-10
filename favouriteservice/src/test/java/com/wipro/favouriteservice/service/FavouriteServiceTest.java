package com.wipro.favouriteservice.service;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.wipro.favouriteservice.exception.PlayerAlreadyExistsException;
import com.wipro.favouriteservice.exception.PlayerNotFoundException;
import com.wipro.favouriteservice.model.Player;
import com.wipro.favouriteservice.model.User;
import com.wipro.favouriteservice.repository.FavouriteRepository;
import com.wipro.favouriteservice.service.FavouriteServiceImpl;

import junit.framework.Assert;

public class FavouriteServiceTest {

	@Mock
	private FavouriteRepository favRepository;

	@InjectMocks
	public FavouriteServiceImpl favouriteServiceImpl;

	private User user;

	private Player cPlayer;

	private List list;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		cPlayer = new Player();
		
		cPlayer.setPlayerName("sachin");
		cPlayer.setMatches(23);
		cPlayer.setRuns(555);
		cPlayer.setHundreds(129);
		cPlayer.setFifties(50);
//		cPlayer.setCountry("india");
		list = new ArrayList();
		list.add(cPlayer);

		user = new User();
		user.setUsername("saibabu");
		user.setPlayerList(list);
	}

	@After
	public void delete() {
		user = null;
	}

	@Test
	public void savePlayerToFavoriteListTest() throws PlayerAlreadyExistsException {

		user = new User();
		user.setUsername("santhosh");
		
		Mockito.when(favRepository.findByUsername(user.getUsername())).thenReturn(user);
		User fetchedUser = favouriteServiceImpl.savePlayerToFavorite(cPlayer, user.getUsername());
		Assert.assertEquals(fetchedUser, user);
		verify(favRepository, timeout(1)).findByUsername(user.getUsername());
		verify(favRepository, times(1)).save(user);
	}

	@Test
	public void deletePlayerFromFavoriteListTest() throws  PlayerNotFoundException {
		Mockito.when(favRepository.findByUsername(user.getUsername())).thenReturn(user);
		User fetchedUser = favouriteServiceImpl.deletePlayerFromFavorite(cPlayer.getPlayerName(),user.getUsername());
		Assert.assertEquals(fetchedUser, user);
		verify(favRepository, timeout(1)).findByUsername(user.getUsername());
		verify(favRepository, times(1)).save(user);

	}
	
	@Test
	public void testgetAllFavoriteList() throws Exception {
		Mockito.when(favRepository.findByUsername(user.getUsername())).thenReturn(user);
		List fetchedList = favouriteServiceImpl.getPlayerList(user.getUsername());
		Assert.assertEquals(fetchedList, list);
		verify(favRepository, times(1)).findByUsername(user.getUsername());

	}

}

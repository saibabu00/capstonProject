package com.wipro.favouriteservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wipro.favouriteservice.model.User;

public interface FavouriteRepository extends MongoRepository<User, String> {

	public User findByUsername(String username);
	
}

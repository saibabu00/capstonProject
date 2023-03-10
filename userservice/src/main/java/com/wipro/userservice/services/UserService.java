package com.wipro.userservice.services;

import java.util.List;

import com.wipro.userservice.exception.UserAlreadyExsitsException;
import com.wipro.userservice.exception.UserNotFoundException;
import com.wipro.userservice.model.User;

public interface UserService {

	public List<User> getAllUsers() throws UserNotFoundException;
	
	User saveUser(User user) throws UserAlreadyExsitsException;

	User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
	
	User upadteUser(String userId, User user) throws UserNotFoundException;
	public User getUserById(String userId) throws UserNotFoundException;


}

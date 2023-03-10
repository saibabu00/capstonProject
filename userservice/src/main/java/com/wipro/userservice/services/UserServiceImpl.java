package com.wipro.userservice.services;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.userservice.exception.UserAlreadyExsitsException;
import com.wipro.userservice.exception.UserNotFoundException;
import com.wipro.userservice.model.User;
import com.wipro.userservice.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;


	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public User saveUser(User user) throws UserAlreadyExsitsException {
		Optional<User> existingUser = userRepo.findById(user.getUserId());
		if (existingUser.isPresent()) {
			throw new UserAlreadyExsitsException("User with id already exists");
		}
		userRepo.save(user);
		return user;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = userRepo.findByUserIdAndPassword(userId, password);
		if (null == user) {
			throw new UserNotFoundException("UserId and Password mismatch");
		}
		return user;
	}

	@Override
	public User upadteUser(String userId, User user) throws UserNotFoundException {
		try {
		User fetchedUser=userRepo.findById(userId).get();
		fetchedUser.setUserId(user.getUserId());
		fetchedUser.setFirstName(user.getFirstName());
		fetchedUser.setLastName(user.getLastName());
		fetchedUser.setEmail(user.getEmail());
		fetchedUser.setPassword(user.getPassword());
		userRepo.save(fetchedUser);
		return fetchedUser;
		}
		catch (Exception e) {
			throw new UserNotFoundException("User doesn't exists");
		}
	}


	@Override
	public User getUserById(String userId) throws UserNotFoundException {
		User fetchedUser=userRepo.findById(userId).get();
		if(fetchedUser == null) {
			throw new UserNotFoundException("User doesn't exists");
		}
		return fetchedUser;
	
	}

	@Override
	public List<User> getAllUsers() throws UserNotFoundException {
		// TODO Auto-generated method stub
		try {
		 List<User> users = new ArrayList<User>();
		 userRepo.findAll().forEach(user -> users.add(user));
		return users;
	} 
	catch (Exception e){
		throw new UserNotFoundException("User doesn't exists");
	}
		
	}
	
	
}
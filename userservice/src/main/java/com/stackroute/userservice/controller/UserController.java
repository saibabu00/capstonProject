package com.stackroute.userservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import com.stackroute.userservice.services.SecurityTokenGenerator;
import com.stackroute.userservice.services.UserService;


@RestController
@RequestMapping("/api/v1/userservice")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private SecurityTokenGenerator tokenGenerator;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			userService.saveUser(user);
			return new ResponseEntity<String>("User registered successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("{ \" message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User loginDetail) {
		try {
			if (null == loginDetail.getUserId() || null == loginDetail.getPassword()) {
				throw new Exception("User Id or Password canot be empty.");
			}
			User user = userService.findByUserIdAndPassword(loginDetail.getUserId(), loginDetail.getPassword());
			Map<String, String> map = tokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("{ \" message\": \"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable String id){
		try {
			return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
		}catch (UserNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?> update(@RequestBody User newData ) throws UserNotFoundException
	{
		return new ResponseEntity<User>(userRepo.save(newData), HttpStatus.OK);	
		
	}
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers(){
		try {
			List<User> userList = userService.getAllUsers();
			return new ResponseEntity<>(userList,HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<String>("{ \" message\": \"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
			
		}
	}
}


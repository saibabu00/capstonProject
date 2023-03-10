package com.wipro.userservice.services;

import java.util.Map;

import com.wipro.userservice.model.User;



public interface SecurityTokenGenerator {

	Map<String, String> generateToken(User user);
}


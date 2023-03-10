package com.wipro.admin.exceptions;

public class PlayerNotFoundException extends Exception{

	private static final long serialVersionUID = 4959275095919203330L;
	
	public PlayerNotFoundException(String message) {
		super(message);
	}

}

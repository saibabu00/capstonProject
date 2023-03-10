package com.stackroute.favouriteservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT , reason="Favorites are empty.")
public class FavouritesAreEmptyException extends Exception{

}

package com.wipro.admin.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.wipro.admin.model.Player;
import com.wipro.admin.model.User;
import com.wipro.admin.service.AdminService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
    WebClient webClient;
	
	@PostConstruct
	public void init() {
		webClient = WebClient.builder().baseUrl("http://localhost:8081/api/v1/userservice").
				defaultHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE).build();
		
		
	}
	@GetMapping("/getUsers")
	public Flux<User> getAllUsers(){
		return webClient.get().uri("/getAllUsers").retrieve().bodyToFlux(User.class);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllPlayers(){
		List<Player> playerList = adminService.getAllPlayers();
		if(playerList == null) {
			return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Player>>(playerList,HttpStatus.OK);
		}
		
		}
	@PostMapping("/add")
	public ResponseEntity<?> addPlayer(@RequestBody Player player){
		try {
			adminService.addPlayer(player);
			return new ResponseEntity<String>("Player added successfully",HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("{ \" message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
			}
		
	}
	
	@GetMapping("/search/{player}")
	public ResponseEntity<?> searchPlayer(@PathVariable String player){
		try {
			List<Player> playerList = adminService.searchPlayer(player);
			return new ResponseEntity<List<Player>>(playerList,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("{ \" message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
			}
		}
	
	@DeleteMapping("/delete/{pid}")
	public ResponseEntity<?> deletePlayer(@PathVariable int pid){
		try {
			adminService.deletePlayer(pid);
			return new ResponseEntity<String>("Player deleted successfully",HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("{ \" message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
			
		}
	}
	@PutMapping("/update/{pid}")
	public ResponseEntity<?> updatePlayer(@PathVariable int pid,@RequestBody Player player){
		try {
			adminService.UpdatePlayer(pid, player);
			return new ResponseEntity<String>("Player updated",HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("{ \" message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
			
		}
	}

}

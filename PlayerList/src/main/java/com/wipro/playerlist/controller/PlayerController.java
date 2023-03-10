package com.wipro.playerlist.controller;

//import java.util.List;

import javax.annotation.PostConstruct;

//import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.wipro.playerlist.model.Player;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//import com.stackroute.recommendations.model.PlayerResponse;
//import com.wipro.admin.model.Player;
//import com.wipro.admin.repository.AdminRepository;
//import com.wipro.admin.service.AdminService;
//

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	WebClient webClient;
	
	@PostConstruct
	public void init() {
		webClient = WebClient.builder().baseUrl("http://localhost:8089/admin").
				defaultHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE).build();
		
		
	}
	@GetMapping("/getPlayers")
	public Flux<Player> getAllPlayers(){
		return webClient.get().uri("/getAll").retrieve().bodyToFlux(Player.class);
	}
	
	@GetMapping("/searchPlayer/{playerName}")
	public Flux<Player> searchPlayer(@PathVariable String playerName){
		return webClient.get().uri("/search/" + playerName).retrieve().bodyToFlux(Player.class);
	}
	
	
//	public Flux<Player> getAllPlayers(){
//		Flux<Player> response = webClient.get().uri("/getAll").exchangeToFlux(response)->{
//			(response.status)
//		}
		
//	}

	
}

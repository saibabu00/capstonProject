package com.wipro.admin.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wipro.admin.model.Player;

@Repository
public interface AdminRepository extends JpaRepository<Player, Integer> {
	
	@Query("SELECT p FROM Player p WHERE "+ "p.playerName LIKE CONCAT('%',:PlayerName, '%')")
	List<Player> findByPlayerNameIgnoreCase(String PlayerName) ;

}

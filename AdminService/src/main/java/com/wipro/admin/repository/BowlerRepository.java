package com.wipro.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wipro.admin.model.Bowler;


public interface BowlerRepository extends JpaRepository<Bowler, Integer> {
	
	@Query("SELECT b FROM Bowler b WHERE "+ "b.bowlerName LIKE CONCAT('%',:bowlerName, '%')")
	List<Bowler> findByBowlerNameIgnoreCase(String bowlerName);

}

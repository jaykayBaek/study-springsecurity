package com.eazybytes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.model.Cards;

public interface CardsRepository extends JpaRepository<Cards, Long> {
	
	List<Cards> findByCustomerId(int customerId);

}

package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Relation;
import com.example.model.Seat;

public interface SeatDao extends JpaRepository<Seat, String> {

	Seat findByRelation(Relation relation);
	

}

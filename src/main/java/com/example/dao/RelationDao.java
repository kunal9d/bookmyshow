package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Cinema;
import com.example.model.Movie;
import com.example.model.Relation;
import com.example.model.Show;

public interface RelationDao extends JpaRepository<Relation, String> {

	List<Relation> findByMovie(Movie movie);

	List<Relation> findByCinema(Cinema cinema);
	
	List<Relation> findByShow(Show show);

	void deleteByMovie(Movie movie);
	void deleteByShow(Show show);
	void deleteByCinema(Cinema cinema);
	
	

	
}

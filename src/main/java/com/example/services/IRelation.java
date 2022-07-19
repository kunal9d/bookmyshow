package com.example.services;

import java.util.List;

import com.example.model.Cinema;
import com.example.model.Movie;
import com.example.model.Relation;
import com.example.model.Show;

public interface IRelation {

	//Relation addRelation(Relation relation);

	List<Relation> findByMovie(Movie movie);

	List<Relation> findAllRelations();

	List<Relation> findbyCinema(Cinema cinema);

	List<Relation> findbyShow(Show show);

	void deleteRelationById(String id);

	void deleteRelationByMovie(Movie movie);

	void deleteRelationByCinema(Cinema cinema);

	void deleteRelationByShow(Show show);

	Relation findById(String rId);

	Relation addRelation(Movie movie, Cinema cinama, Show show);

	//public List<Relation> getCinemaByMovieName(String movieName);
}

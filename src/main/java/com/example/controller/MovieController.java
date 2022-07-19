package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Movie;
import com.example.services.MovieImplementation;
@RestController
public class MovieController {
	@Autowired
	private MovieImplementation mi;
	Logger logger= org.slf4j.LoggerFactory.getLogger(MovieController.class);
	@PostMapping("addMovie")
    public ResponseEntity<Movie> insertMovie(@RequestBody Movie movie) {
		Movie mov=mi.createMovie(movie);
		logger.info("-----------------------Movie Added-------------------");
		return new ResponseEntity<Movie>(mov,HttpStatus.CREATED);	
	}
	@GetMapping("movies")
	public List<Movie> getAllMovies() {
		logger.info("-----------------------List Of Movies Returned-------------------");
		return mi.getMovies();
	}
	@GetMapping("movies/{movieId}") // By GET
	public ResponseEntity<Movie> getMovieById(@PathVariable("movieId") String Id) {
		Movie mov=mi.getMovieById(Id);
		logger.info("-----------------------Check Out for Movie-------------------");
		return new ResponseEntity<Movie>(mov,HttpStatus.OK);
	}
	
	@DeleteMapping("deleteMovies/{movieId}") // By Delete
	public ResponseEntity<Void> deleteMovieById(@PathVariable("movieId") String Id) {
		mi.deleteMovie(Id);
		logger.info("-----------------------Delete Movie-------------------");
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	@DeleteMapping("deleteMovies") // By Delete
	public ResponseEntity<Void> deleteAllMovies() {
		mi.deleteAllMovies();
		logger.info("-----------------------Delete Movie-------------------");
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	
	

}

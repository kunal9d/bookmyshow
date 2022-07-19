package com.example.services;

import java.util.List;

import com.example.model.Movie;

public interface IMovie {
	
	public Movie createMovie(Movie movie);
	
	public List<Movie> getMovies();
	
	public  void  deleteMovie(String movieId);
	
	public Movie getMovieById(String movieId);
	
	public  void  deleteAllMovies();

}

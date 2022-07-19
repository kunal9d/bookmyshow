package com.example.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.MovieDao;
import com.example.exception.EmptyInputException;
import com.example.model.Movie;

@Service
public class MovieImplementation implements IMovie {
	@Autowired
	private MovieDao md;

	@Override
	public Movie createMovie(Movie movie) {
		if(movie.getMovieName().isEmpty() || movie.getMovieName().length() == 0) {
			throw new EmptyInputException("601", "Input Fields can't be empty");
		}
		movie.setMovieName(movie.getMovieName().toLowerCase());
	
		return md.save(movie);
	}

	@Override
	public List<Movie> getMovies() {
		// TODO Auto-generated method stub
		return md.findAll();
	}


	@Override
	public void deleteMovie(String movieId) {
		// TODO Auto-generated method stub
		if (md.existsById(movieId.toLowerCase()))
			md.deleteById(movieId.toLowerCase());
		else 
			throw new EmptyInputException("602", " This movie doesn't exists");
			
		}
		
	

	@Override
	public Movie getMovieById(String movieId) {
		// TODO Auto-generated method stub
		return md.findById(movieId.toLowerCase()).get();
	}

	@Override
	public void deleteAllMovies() {
		// TODO Auto-generated method stub
		md.deleteAll();
		
	}

	
	
	
	

}

package com.example.services;

import java.util.List;

import com.example.model.Cinema;

public interface ICinema {
	
	public Cinema addCinema(Cinema cinema);
	
	public List<Cinema> getCinemas();
	
	public void deleteCinema(String cinemaId);
	
	public Cinema getCinemaById(String cinemaId);
	
	public void deleteAllCinemas();

}

package com.example.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CinemaDao;
import com.example.exception.EmptyInputException;
import com.example.model.Cinema;

@Service
public class CinemaImplementation implements ICinema {
	@Autowired
	private CinemaDao cd;
	
	@Override
	public Cinema addCinema(Cinema cinema) {
		if(cinema.getCinemaName().isEmpty() || cinema.getCinemaName().length() == 0) {
			throw new EmptyInputException("603", "Input Fields can't be empty");
		}
		cinema.setCinemaName(cinema.getCinemaName().toLowerCase());
		return cd.save(cinema);
	}
	@Override
	public List<Cinema> getCinemas() {
		// TODO Auto-generated method stub
		return cd.findAll();
	}
	
	@Override
	public void deleteCinema(String cinemaId) {
		// TODO Auto-generated method stub
		if (cd.existsById(cinemaId.toLowerCase()))
			cd.deleteById(cinemaId.toLowerCase());
		else 
			throw new EmptyInputException("604", " This cinema doesn't exists");
			
		}		
	
	@Override
	public Cinema getCinemaById(String cinemaId) {
		// TODO Auto-generated method stub
		return cd.findById(cinemaId.toLowerCase()).get();
	}
	@Override
	public void deleteAllCinemas() {
		// TODO Auto-generated method stub
		cd.deleteAll();		
	}
}

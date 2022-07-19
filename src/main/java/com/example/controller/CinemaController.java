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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Cinema;
import com.example.services.CinemaImplementation;

@RestController
public class CinemaController {
	@Autowired
	private CinemaImplementation ci;
	Logger logger= org.slf4j.LoggerFactory.getLogger(CinemaController.class);
	@PostMapping("addCinema")
	public ResponseEntity<Cinema> insertCinema(@RequestBody Cinema cinema) {
		Cinema cin=ci.addCinema(cinema);
		logger.info("-----------------------Cinema Created-------------------");
		return new ResponseEntity<Cinema>(cin,HttpStatus.CREATED);		
	}
	@GetMapping("cinemas")
	public List<Cinema> getAllCinemas() {
		logger.info("-----------------------list of Cinemas return-------------------");
		return ci.getCinemas();
	}
	@GetMapping("cinemas/{cinemaId}") // By GET
	public ResponseEntity<Cinema> getCinemaById(@PathVariable("cinemaId") String Id) {
		Cinema cin=ci.getCinemaById(Id);
		logger.info("-----------------------Check Out For Cinema-------------------");
		return new ResponseEntity<Cinema>(cin,HttpStatus.OK);
	}
	
	@DeleteMapping("deleteCinemas/{cinemaId}") // By Delete
	public ResponseEntity<Void> deleteCinemaById(@PathVariable("cinemaId") String Id) {
		ci.deleteCinema(Id);
		logger.info("-----------------------Delete Cinema-------------------");
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	@DeleteMapping("deleteCinemas") // By Delete
	public ResponseEntity<Void> deleteAllCinemas() {
		ci.deleteAllCinemas();
		logger.info("-----------------------Delete Cinema-------------------");
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
}

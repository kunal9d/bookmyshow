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

import com.example.model.Show;
import com.example.services.ShowImplementation;

@RestController
public class ShowController {
	@Autowired
	private ShowImplementation si;
	Logger logger= org.slf4j.LoggerFactory.getLogger(ShowController.class);

	@PostMapping("addShow")
	public ResponseEntity<Show> insertShow(@RequestBody Show show) {
		Show sho = si.createShow(show);
		logger.info("-----------------------Show Created-------------------");
		return new ResponseEntity<Show>(sho, HttpStatus.CREATED);
	}

	@GetMapping("shows")
	public List<Show> getAllShows() {
		logger.info("-----------------------List of Shows Return-------------------");
		return si.getShows();
	}

	@GetMapping("shows/{showId}") // By GET
	public ResponseEntity<Show> getShowById(@PathVariable("showId") String Id) {
		Show sho = si.getShowById(Id);
		logger.info("-----------------------List of Shows Return by id-------------------");
		return new ResponseEntity<Show>(sho, HttpStatus.OK);
	}


	@DeleteMapping("deleteShows/{showId}") // By Delete
	public ResponseEntity<Void> deleteShowById(@PathVariable("showId") String Id) {
		si.deleteShow(Id);
		logger.info("-----------------------Delete Show-------------------");
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@DeleteMapping("deleteShows") // By Delete
	public ResponseEntity<Void> deleteAllShows() {
		si.deleteAllShows();
		logger.info("-----------------------Delete All Shows-------------------");
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
}

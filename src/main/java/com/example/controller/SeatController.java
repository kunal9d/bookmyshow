package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Relation;
import com.example.model.Seat;
import com.example.services.RelationImplementation;
import com.example.services.SeatImplementation;

@RestController
public class SeatController {
	@Autowired
	private RelationImplementation ri;
	@Autowired
	private SeatImplementation si;
	Logger logger = org.slf4j.LoggerFactory.getLogger(SeatController.class);

	@PostMapping("seats/{rId}/{seatcount}")
	public ResponseEntity<Seat> addtotalseattorelation(@PathVariable("rId") String rid,
			@Valid @PathVariable("seatcount") int seatcount) {
		Relation relation = ri.findById(rid);
		Seat s = si.addTotalSeat(relation, seatcount);
		logger.info("-----------------------Seat id Created-------------------");
		return new ResponseEntity<Seat>(s, HttpStatus.CREATED);
	}

	@GetMapping("seats")
	public List<Seat> getAllSeats() {
		logger.info("-----------------------List Of seats return-------------------");
		return si.findAllSeats();
	}

	@PutMapping("bookSeat/{sId}/{seatcount}")
	public ResponseEntity<Seat> bookSeat(@PathVariable("sId") String sid,
			@Valid @PathVariable("seatcount") int seatcount) {
		Seat seat = si.findSeatById(sid);
		Seat s = si.bookSeats(seat, seatcount);
		logger.info("-----------------------Seat Booked-------------------");
		return new ResponseEntity<Seat>(s, HttpStatus.CREATED);
	}

	@PutMapping("cancelSeat/{sId}/{seatcount}")
	public ResponseEntity<Seat> cancelSeat(@PathVariable("sId") String sid, @PathVariable("seatcount") int seatcount) {
		Seat seat = si.findSeatById(sid);

		Seat s = si.cancelSeats(seat, seatcount);
		logger.info("-----------------------Seats Cancelled-------------------");
		return new ResponseEntity<Seat>(s, HttpStatus.CREATED);

	}

}

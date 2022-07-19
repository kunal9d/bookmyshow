package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.SeatDao;
import com.example.exception.EmptyInputException;
import com.example.model.Relation;
import com.example.model.Seat;

@Service
public class SeatImplementation implements ISeat {

	@Autowired
	private SeatDao sd;


	@Override
	public List<Seat> findAllSeats() {

		return sd.findAll();
	}

	@Override
	public Seat addTotalSeat(Relation relation,int seatcount) {
		
		List<Seat> seats = sd.findAll();
		int count = (int)seats.stream().filter(c->c.getRelation().getRelationId().equalsIgnoreCase(relation.getRelationId())).count();
		if(count==0) {
		Seat seat = new Seat();
		seat.setRelation(relation);
		seat.setTotalSeats(seatcount);
		seat.setSeatsTaken(0);
		return sd.save(seat);
		}
		throw new EmptyInputException("608","Seat Already entered");
	}

	@Override
	public Seat findSeatById(String sId) {

		return sd.findById(sId).get();
	}

	@Override
	public Seat bookSeats(Seat seat,int seatcount) {
		if(seat.getSeatsTaken()+seatcount<=seat.getTotalSeats()) {
			int c = seat.getSeatsTaken();
			seat.setSeatsTaken(c+seatcount);
		return sd.save(seat);
		}
		throw new EmptyInputException("609","No More Seats Available");
	}

	@Override
	public Seat findSeatByRelationId(Relation relation) {

		return sd.findByRelation(relation);
	}
	@Override
	public Seat cancelSeats(Seat seat,int seatcount) {
		if(seat.getSeatsTaken()-seatcount>=0) {
			int c = seat.getSeatsTaken();
			seat.setSeatsTaken(seatcount-c);
		return sd.save(seat);
	}
		throw new EmptyInputException("610","Seats can't be Canceled");
	}
	@Override
	public Seat updateSeat(Seat seat) {

		return sd.save(seat);
	}


}

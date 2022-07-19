package com.example.services;

import java.util.List;

import com.example.model.Relation;
import com.example.model.Seat;

public interface ISeat {

	List<Seat> findAllSeats();

	Seat addTotalSeat(Relation relation, int seatcount);

	Seat findSeatById(String sId);

	Seat findSeatByRelationId(Relation relation);

	Seat bookSeats(Seat seat, int seatcount);

	Seat cancelSeats(Seat seat, int seatcount);

	Seat updateSeat(Seat seat);

}

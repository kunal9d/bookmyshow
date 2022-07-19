package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.example.generator.SeatGenerator;

@Entity
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seat_seq")
	@GenericGenerator(name = "seat_seq", strategy = "com.example.generator.SeatGenerator", parameters = {
			@Parameter(name = SeatGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = SeatGenerator.VALUE_PREFIX_PARAMETER, value = "S_"),
			@Parameter(name = SeatGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })
	@Column(name = "seatId", updatable = false, nullable = false)
	private String seatId;

	public String getSeatId() {
		return seatId;
	}

	@OneToOne
	private Relation relation;
	@Min(value = 0, message = "total seats should be numeric form")
	private int totalSeats;
	@Min(value = 0, message = "Enter valid Seats In Numeric Form")
	private int seatsTaken;

	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seat(String seatId, Relation relation,
			@NotEmpty @Min(value = 0, message = "total seats should be numeric form") int totalSeats,
			@Min(value = 0, message = "Enter valid Seats In Numeric Form") int seatsTaken) {
		super();
		this.seatId = seatId;
		this.relation = relation;
		this.totalSeats = totalSeats;
		this.seatsTaken = seatsTaken;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getSeatsTaken() {
		return seatsTaken;
	}

	public void setSeatsTaken(int seatsTaken) {
		this.seatsTaken = seatsTaken;
	}

}

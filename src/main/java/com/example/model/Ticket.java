package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.example.generator.TicketGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_seq")
	@GenericGenerator(name = "ticket_seq", strategy = "com.example.generator.TicketGenerator", parameters = {
			@Parameter(name = TicketGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = TicketGenerator.VALUE_PREFIX_PARAMETER, value = "T_"),
			@Parameter(name = TicketGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })
	@Column(name = "tid", updatable = false, nullable = false)
	private String tid;
	@OneToOne
	private Seat seat;
	// private Relation relation;
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties("ticket")
	private User user;
	private int ticketPrice;

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private int seatsBooked;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

//	public Relation getRelation() {
//		return relation;
//	}
//
//	public void setRelation(Relation relation) {
//		this.relation = relation;
//	}

	public int getSeatsBooked() {
		return seatsBooked;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}

}

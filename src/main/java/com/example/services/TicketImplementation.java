package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.TicketDao;
import com.example.model.Ticket;

@Service
public class TicketImplementation implements ITicket {
	@Autowired
	private TicketDao td;
	@Override
	public Ticket createTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return td.save(ticket);
	}
	@Override
	public Ticket findTicketById(String tid) {
		// TODO Auto-generated method stub
		return td.findById(tid).get();
	}
	@Override
	public void updateTicket(Ticket t) {
		// TODO Auto-generated method stub
		td.save(t);
	}
}

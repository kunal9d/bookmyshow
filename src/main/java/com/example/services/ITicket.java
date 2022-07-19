package com.example.services;

import com.example.model.Ticket;

public interface ITicket {

	Ticket createTicket(Ticket ticket);

	Ticket findTicketById(String tid);

	void updateTicket(Ticket s);
}

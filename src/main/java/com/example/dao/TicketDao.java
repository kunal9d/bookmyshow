package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Ticket;

public interface TicketDao extends JpaRepository<Ticket, String> {

}

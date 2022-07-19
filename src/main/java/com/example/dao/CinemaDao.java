package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Cinema;

public interface CinemaDao extends JpaRepository<Cinema, String> {

}

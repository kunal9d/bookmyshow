package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Movie;

public interface MovieDao extends JpaRepository<Movie, String> {

}

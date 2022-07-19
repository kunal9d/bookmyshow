package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Show;

public interface ShowDao extends JpaRepository<Show, String> {

}

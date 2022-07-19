package com.example.services;

import java.util.List;

import com.example.model.Show;

public interface IShow {

	public Show createShow(Show show);

	public List<Show> getShows();

	

	public void deleteShow(String showId);

	public Show getShowById(String showId);

	public void deleteAllShows();

}

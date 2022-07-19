package com.example.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.ShowDao;
import com.example.exception.EmptyInputException;
import com.example.model.Show;
@Service
public class ShowImplementation implements IShow {
	@Autowired
	private ShowDao sd;
	@Override
	public Show createShow(Show show) {
		// TODO Auto-generated method stub
		if(show.getShowTime().isEmpty() || show.getShowTime().length() == 0) {
			throw new EmptyInputException("605", "Input Fields can't be empty");
		}
		show.setShowTime(show.getShowTime().toLowerCase());
	
		
		return sd.save(show);
	}

	@Override
	public List<Show> getShows() {
		// TODO Auto-generated method stub
		return sd.findAll();
	}



	@Override
	public void deleteShow(String showId) {
		// TODO Auto-generated method stub
		if (sd.existsById(showId.toLowerCase()))
			sd.deleteById(showId.toLowerCase());
		else 
			throw new EmptyInputException("606", " This Show doesn't exists");
			
	}

	@Override
	public Show getShowById(String showId) {
		// TODO Auto-generated method stub
		return sd.findById(showId.toLowerCase()).get();
	}

	@Override
	public void deleteAllShows() {
		// TODO Auto-generated method stub
		sd.deleteAll();
		
	}

}

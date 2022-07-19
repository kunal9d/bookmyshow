package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.RelationDao;
import com.example.exception.EmptyInputException;
import com.example.model.Cinema;
import com.example.model.Movie;
import com.example.model.Relation;
import com.example.model.Show;

@Service
public class RelationImplementation implements IRelation {
	@Autowired
	private RelationDao rd;

	@Override
	public Relation addRelation(Movie movie, Cinema cinema,Show show) {
		List<Relation> r= rd.findAll();
		int count=(int)r.stream().filter(c->c.getCinema()==cinema && c.getShow()==show).count();
		if (count==0) {
		Relation relation =new Relation();
		movie.getRel().add(relation);
		cinema.getRel().add(relation);
		show.getRel().add(relation);
		relation.setCinema(cinema);
		relation.setMovie(movie);
		relation.setShow(show);
		return rd.save(relation);
			
		}
		throw new EmptyInputException("606","SHOW ALREADY ON");
	}

	@Override
	public List<Relation> findByMovie(Movie movie) {
		return rd.findByMovie(movie);
	}

	@Override
	public List<Relation> findAllRelations() {
		return rd.findAll();
	}

	@Override
	public List<Relation> findbyCinema(Cinema cinema) {
		return rd.findByCinema(cinema);
	}

	@Override
	public List<Relation> findbyShow(Show show) {
		return rd.findByShow(show);
	}

	@Override
	public void deleteRelationById(String id) {
		rd.deleteById(id);
	}

	@Override
	public void deleteRelationByMovie(Movie movie) {
		rd.deleteByMovie(movie);
	}

	@Override
	public void deleteRelationByCinema(Cinema cinema) {
		rd.deleteByCinema(cinema);
	}

	@Override
	public void deleteRelationByShow(Show show) {
		rd.deleteByShow(show);
	}

	@Override
	public Relation findById(String rId) {

		return rd.findById(rId).get();
	}

}

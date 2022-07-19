package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Cinema;
import com.example.model.Movie;
import com.example.model.Relation;
import com.example.model.Show;
import com.example.services.CinemaImplementation;
import com.example.services.MovieImplementation;
import com.example.services.RelationImplementation;
import com.example.services.ShowImplementation;
@RestController
public class RelationController {
	@Autowired
	private RelationImplementation ri;
	@Autowired
	private MovieImplementation mi;
	@Autowired
	private CinemaImplementation ci;
	@Autowired
	private ShowImplementation si;
	Logger logger= org.slf4j.LoggerFactory.getLogger(RelationController.class);
	
	@PostMapping("addRelation/{mid}/{cid}/{sid}")
    public ResponseEntity<Relation> addRelation(@PathVariable("mid") String mid,@PathVariable("cid") String cid,@PathVariable("sid") String sid) {
		Movie movie =mi.getMovieById(mid);
		Cinema cinema = ci.getCinemaById(cid);
		Show show =	si.getShowById(sid);
		
		Relation rel= ri.addRelation(movie,cinema,show);
		logger.info("-----------------------Relation Created-------------------");
		return new ResponseEntity<Relation> (rel,HttpStatus.CREATED);
		}
		
		
	@GetMapping("relations")
	public List<Relation> getAllRelations(){
		logger.info("----------------------- List Of Relation Returned-------------------");
		return ri.findAllRelations();
	}
	
	@GetMapping("relations/movie/{movie}")
	public List<Relation> searchbymovie(@PathVariable("movie") String movie){
		logger.info("-----------------------Search Relation For Movie-------------------");
		return ri.findByMovie(mi.getMovieById(movie.toLowerCase()));
	}	
	@GetMapping("relations/cinema/{cinema}")
	public List<Relation> searchbycinema(@PathVariable("cinema") String cinema){
		logger.info("-----------------------Search Relation For Cinemas-------------------");
		return ri.findbyCinema(ci.getCinemaById(cinema.toLowerCase()));
	}
	@GetMapping("relations/show/{show}")
	public List<Relation> searchbyshow(@PathVariable("show") String show){
		logger.info("-----------------------Search Relation For Show-------------------");
		return ri.findbyShow(si.getShowById(show.toLowerCase()));
	}
	@GetMapping("relations/{rId}")
	public Relation searchbyId(@PathVariable("rId") String rId){
		logger.info("-----------------------Search Relation by Id-------------------");
		return ri.findById(rId);
	}
	

	
}

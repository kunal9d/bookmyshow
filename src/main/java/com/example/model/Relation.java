package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.example.generator.RelationGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Relation {

	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE,generator = "rel_seq")
	@GenericGenerator(name="rel_seq",
			strategy = "com.example.generator.RelationGenerator",
			parameters = {
					@Parameter(name=RelationGenerator.INCREMENT_PARAM,value="1"),
					@Parameter(name=RelationGenerator.VALUE_PREFIX_PARAMETER,value="R_"),
					@Parameter(name=RelationGenerator.NUMBER_FORMAT_PARAMETER,value="%03d")
			}
			)
	@Column(name="relationId",updatable = false, nullable=false )
	private String relationId;
	@ManyToOne(fetch =FetchType.EAGER)
	@JsonIgnoreProperties("rel")
	private Movie movie;
	@ManyToOne(fetch =FetchType.EAGER)
	@JsonIgnoreProperties("rel")
	private Show show;
	@ManyToOne(fetch =FetchType.EAGER)
	@JsonIgnoreProperties("rel")
	private Cinema cinema;
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
}

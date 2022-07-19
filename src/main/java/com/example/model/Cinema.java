package com.example.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Cinema {
	

	@Id
	private String cinemaName;
	@OneToMany(fetch =FetchType.EAGER,mappedBy = "cinema")
	@JsonIgnoreProperties("cinema")
	private List<Relation> rel;
	public List<Relation> getRel() {
		return rel;
	}
	public void setRel(List<Relation> rel) {
		this.rel = rel;
	}
	
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	
}

package com.example.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Show {

	@Id
	private String showTime;
	@OneToMany(fetch =FetchType.EAGER,mappedBy = "show")
	@JsonIgnoreProperties("show")
	private List<Relation> rel;

	public List<Relation> getRel() {
		return rel;
	}

	public void setRel(List<Relation> rel) {
		this.rel = rel;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

}

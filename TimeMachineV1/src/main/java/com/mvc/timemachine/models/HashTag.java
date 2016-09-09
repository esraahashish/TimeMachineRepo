package com.mvc.timemachine.models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hashtags")
public class HashTag {

	@Id
	@GeneratedValue
	@Column(name = "HashTag_Id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "frequency")
	private String frequency;

	@ManyToMany(mappedBy = "eraHashTags")
	private List<Era> hashTagEras = new LinkedList<Era>();
	
	@ManyToMany(mappedBy = "informationHashTags")
	private List<Information> hashTagInformation = new LinkedList<Information>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}


}

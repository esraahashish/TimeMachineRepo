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

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "frequency")
	private Long frequency;

	@ManyToMany(mappedBy = "eraHashTags")
	private List<Era> hashTagEras = new LinkedList<Era>();
	
	@ManyToMany(mappedBy = "informationHashTags")
	private List<Information> hashTagInformation = new LinkedList<Information>();
	
	@ManyToMany(mappedBy = "postHashTags")
	private List<Post> hashTagposts = new LinkedList<Post>();
	 

	public Long getId() {
		return id;
	}

	public List<Era> getHashTagEras() {
		return hashTagEras;
	}

	public void setHashTagEras(List<Era> hashTagEras) {
		this.hashTagEras = hashTagEras;
	}

	public List<Information> getHashTagInformation() {
		return hashTagInformation;
	}

	public void setHashTagInformation(List<Information> hashTagInformation) {
		this.hashTagInformation = hashTagInformation;
	}

	public List<Post> getHashTagposts() {
		return hashTagposts;
	}

	public void setHashTagposts(List<Post> hashTagposts) {
		this.hashTagposts = hashTagposts;
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

	public Long getFrequency() {
		return frequency;
	}

	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}


}

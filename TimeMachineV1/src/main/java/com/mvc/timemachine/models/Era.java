package com.mvc.timemachine.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "eras")
public class Era {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "era")
	@OrderBy("date ASC")
	private List<Information> eraInfo = new LinkedList<Information>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "era")
    private List<Post> posts = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "era")
	private List<Sound> eraSounds = new LinkedList<Sound>();
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "era")
	private List<Photo> eraPhotos = new LinkedList<Photo>();
	
	@ManyToMany
	@JoinTable(name = "Era_HashTag", joinColumns = { @JoinColumn(name = "Era_Id") }, inverseJoinColumns = { @JoinColumn(name = "HashTag_Id") })
	private List<HashTag> eraHashTags = new LinkedList<HashTag>();

	public List<Information> getEraInfo() {
		return eraInfo;
	}

	public void setEraInfo(List<Information> eraInfo) {
		this.eraInfo = eraInfo;
	}

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
}

package com.mvc.timemachine.models;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "information")
public class Information {
	
	
	@Id
	@GeneratedValue
	@Column(name = "Information_Id")
	private Long id;

	@Column(name = "content")
	private String content;
	
	@Column(name = "date")
	private LocalDateTime date;

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@ManyToOne(optional = false , fetch = FetchType.EAGER)
	@JoinColumn(name = "era_id")
	private Era era;

	@ManyToMany
	@JoinTable(name = "Information_HashTag", joinColumns = { @JoinColumn(name = "Information_Id") }, inverseJoinColumns = { @JoinColumn(name = "HashTag_Id") })
	private List<HashTag> informationHashTags = new LinkedList<HashTag>();
	
	public Era getEra() {
		return era;
	}

	public void setEra(Era era) {
		this.era = era;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

}

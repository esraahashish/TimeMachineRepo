package com.mvc.timemachine.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "sounds")
public class Sound {
	

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "url")
	private String url;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "era_id")
	private Era era;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

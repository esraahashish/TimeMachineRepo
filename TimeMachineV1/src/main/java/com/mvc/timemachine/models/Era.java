package com.mvc.timemachine.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;


@Entity(name = "era")
public class Era {
	
	@Id
	@GeneratedValue
	@Column(name = "Era_Id")
	private Long id;

	@Column(name = "name")
	private String name;
	
	//@Column(name = "date")
	//private LocalDateTime startDate;
	
	@Column(name = "start_info_ind")
	private Integer startInfoInd;
	
	@Column(name = "today_photo_index")
	private Integer photoInd;
	
	@Column(name = "today_sound_index")
	private Integer soundInd;
	
	@Column(name = "info_increase_rate")
	private Integer infoIncreaseRate;
	
	@Column(name = "increase_days")
	private Integer increaseDays;
	
	@Column(name = "used_days")
	private Integer usedDays;

	public Integer getInfoIncreaseRate() {
		return infoIncreaseRate;
	}

	public void setInfoIncreaseRate(Integer infoIncreaseRate) {
		this.infoIncreaseRate = infoIncreaseRate;
	}

	public Integer getIncreaseDays() {
		return increaseDays;
	}

	public void setIncreaseDays(Integer increaseDays) {
		this.increaseDays = increaseDays;
	}

	public Integer getUsedDays() {
		return usedDays;
	}

	public void setUsedDays(Integer usedDays) {
		this.usedDays = usedDays;
	}

	public Integer getPhotoInd() {
		return photoInd;
	}

	public void setPhotoInd(Integer photoInd) {
		this.photoInd = photoInd;
	}

	public Integer getSoundInd() {
		return soundInd;
	}

	public void setSoundInd(Integer soundInd) {
		this.soundInd = soundInd;
	}

	public Integer getStartInfoInd() {
		return startInfoInd;
	}

	public void setStartInfoInd(Integer startInfoInd) {
		this.startInfoInd = startInfoInd;
	}

	@Column(name = "description")
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "era")
	@OrderBy("date ASC")
	private List<Information> eraInfo = new ArrayList<Information>();
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "era")
	private List<Sound> eraSounds = new ArrayList<Sound>();
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "era")
	private List<Photo> eraPhotos = new ArrayList<Photo>();
	
	@ManyToMany
	@JoinTable(name = "Era_HashTag", joinColumns = { @JoinColumn(name = "Era_Id") }, inverseJoinColumns = { @JoinColumn(name = "HashTag_Id") })
	private List<HashTag> eraHashTags = new ArrayList<HashTag>();
	
	@ManyToMany
	@JoinTable(name = "Era_Users", joinColumns = { @JoinColumn(name = "Era_Id") }, inverseJoinColumns = { @JoinColumn(name = "User_Id") })
	private List<User> eraUsers = new ArrayList<User>();

	public List<Sound> getEraSounds() {
		return eraSounds;
	}

	public void setEraSounds(List<Sound> eraSounds) {
		this.eraSounds = eraSounds;
	}

	public List<Photo> getEraPhotos() {
		return eraPhotos;
	}

	public void setEraPhotos(List<Photo> eraPhotos) {
		this.eraPhotos = eraPhotos;
	}

	public List<HashTag> getEraHashTags() {
		return eraHashTags;
	}

	public void setEraHashTags(List<HashTag> eraHashTags) {
		this.eraHashTags = eraHashTags;
	}

	public List<User> getEraUsers() {
		return eraUsers;
	}

	public void setEraUsers(List<User> eraUsers) {
		this.eraUsers = eraUsers;
	}

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

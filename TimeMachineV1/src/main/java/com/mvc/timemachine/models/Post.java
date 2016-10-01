package com.mvc.timemachine.models;

import java.time.LocalDateTime;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "title", length = 300)
	private String title;
	
	@Lob
	@Column(name = "body", nullable = false)
	private String body;
	
	@Column(name = "date", nullable = false)
	private LocalDateTime date;
	
//	@Column(name = "user_id", nullable = false)
	@ManyToOne(optional = false, fetch=FetchType.EAGER)
	@JoinColumn(name ="user_id")
	private User user;
	
	@ManyToOne(optional = false, fetch=FetchType.EAGER)
	@JoinColumn(name ="era_id")
	private Era era;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    @OrderBy("date ASC")
    private List<Comment> comments = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "Post_HashTags", joinColumns = { @JoinColumn(name = "Post_Id") }, inverseJoinColumns = { @JoinColumn(name = "HashTag_Id") })
	private List<HashTag> postHashTags = new LinkedList<HashTag>();
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Like> likes = new ArrayList<>();

	
	
	public List<HashTag> getPostHashTags() {
		return postHashTags;
	}

	public void setPostHashTags(List<HashTag> postHashTags) {
		this.postHashTags = postHashTags;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Era getEra() {
		return era;
	}

	public void setEra(Era era) {
		this.era = era;
	}
	
	@Override
	public String toString(){
		return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", author=" + user +
                ", date=" + date +
                '}';
	}
	
}

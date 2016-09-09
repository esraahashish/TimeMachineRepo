package com.mvc.timemachine.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Lob
	@Column(name = "commentText", nullable = false)
	@NotBlank
	private String commentText;
	
	@Column(name = "date", nullable = false)
	private LocalDateTime date;
	
//	@Column(name = "user_id", nullable = false)
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name ="user_id")
	private User user;
	
//	@Column(name = "post_id", nullable = false)
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name ="post_id")
	private Post post;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	@Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentText='" + commentText + '\'' +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
	
}

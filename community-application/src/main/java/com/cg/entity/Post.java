package com.cg.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="post_table")
public class Post {
	@Id 
	@GeneratedValue
	private int postId;
	@Lob
	@Column(name = "question", length = 1000)
	@NotNull(message = "Please enter query")
	@NotBlank(message = "Please enter query")
	private String query;
	@Column(name = "date_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime postDateTime;
	@Column(length = 50)
	private String topic;
	@ManyToOne	
	@JoinColumn(name = "developerId")
	private Developer developer;
	//	@OneToMany(mappedBy = "post")
	private Integer noOfViews;



	public Post() {
		super();
	}
	public Post(int postId,
			@NotNull(message = "Please enter query") @NotBlank(message = "Please enter query") String query,
			@NotNull(message = "Please enter date and time") @NotBlank(message = "Please enter date and time") LocalDateTime postDateTime,
			@NotNull(message = "Please enter topic") @NotBlank(message = "Please enter topic") String topic,
			Developer developer, Integer noOfViews) {
		super();
		this.postId = postId;
		this.query = query;
		this.postDateTime = postDateTime;
		this.topic = topic;
		this.developer = developer;
		this.noOfViews = noOfViews;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}

	public LocalDateTime getPostDateTime() {
		return postDateTime;
	}
	public void setPostDateTime(LocalDateTime postDateTime) {
		this.postDateTime = postDateTime;
	}

	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Developer getDeveloper() {
		return developer;
	}
	public void setDeveloper(Developer developerId) {
		this.developer = developerId;
	}

	public Integer getNoOfViews() {
		return noOfViews;
	}
	public void setNoOfViews(Integer noOfViews) {
		this.noOfViews = noOfViews;
	}
}

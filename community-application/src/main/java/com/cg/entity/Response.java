package com.cg.entity;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name = "response_table")
public class Response {
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
	@SequenceGenerator(name="generator",sequenceName="sequence",allocationSize=1 )
	private int responseId;
	@Lob
	@Column
	@NotBlank(message = "Please enter answer")
	@NotNull(message = "Please enter answer")
	private String answer;
	@Column(length=50)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime respDateTime;
	@ManyToOne
	@JoinColumn(name = "postId")
	private Post post;
	@ManyToOne
	@JoinColumn(name = "developerID")
	private Developer developer;
	public int getResponseId() {
		return responseId;
	}
	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public LocalDateTime getRespDateTime() {
		return respDateTime;
	}
	public void setRespDateTime(LocalDateTime respDateTime) {
		this.respDateTime = respDateTime;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public Developer getDeveloper() {
		return developer;
	}
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	public Response(int responseId, String answer, LocalDateTime respDateTime, Post post, Developer developer) {
		super();
		this.responseId = responseId;
		this.answer = answer;
		this.respDateTime = respDateTime;
		this.post = post;
		this.developer = developer;
	}
	public Response() {
	}

}


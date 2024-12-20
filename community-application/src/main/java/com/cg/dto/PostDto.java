package com.cg.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PostDto {
	
	private int postId;
	private String query;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime postDateTime;
	private String topic;
	private int developerId;
	private Integer noOfViews;
	
	
	
	public PostDto() {
		super();
	}
	public PostDto(int postId, String query, LocalDateTime postDateTime, String topic, int developerId,
			Integer noOfViews) {
		super();
		this.postId = postId;
		this.query = query;
		this.postDateTime = postDateTime;
		this.topic = topic;
		this.developerId = developerId;
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
	public int getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}
	public Integer getNoOfViews() {
		return noOfViews;
	}
	public void setNoOfViews(Integer noOfViews) {
		this.noOfViews = noOfViews;
	}
	

}

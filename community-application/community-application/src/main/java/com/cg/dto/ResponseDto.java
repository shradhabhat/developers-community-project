package com.cg.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


public class ResponseDto {

	private int responseId;
	private String answer;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime respDateTime;

	private int postId;

	private int developerId;


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

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}

	public ResponseDto(int responseId, String answer, LocalDateTime respDateTime, int postId, int developerId) {
		super();
		this.responseId = responseId;
		this.answer = answer;
		this.respDateTime = respDateTime;
		this.postId = postId;
		this.developerId = developerId;
	}
	public ResponseDto() {
	}

}

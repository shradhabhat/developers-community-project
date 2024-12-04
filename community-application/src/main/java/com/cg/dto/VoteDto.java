package com.cg.dto;
 
 
public class VoteDto {
	private int voteId;
	private int upVote;
	private int downVote;
	private int voteCount;
	public int getVoteCount() {
		return voteCount;
	}
 
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
 
	private int response;
	private int developer;
 
	

 
	public int getResponse() {
		return response;
	}
 
	public void setResponse(int response) {
		this.response = response;
	}
 
	public int getDeveloper() {
		return developer;
	}
 
	public void setDeveloper(int developer) {
		this.developer = developer;
	}
 
	public int getVoteId() {
		return voteId;
	}
 
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}
 
	public int getUpVote() {
		return upVote;
	}
 
	public void setUpVote(int upVote) {
		this.upVote = upVote;
	}
 
	public int getDownVote() {
		return downVote;
	}
 
	public void setDownVote(int downVote) {
		this.downVote = downVote;
	}
 
	
	public VoteDto(int voteId, int response, int upVote, int downVote, int developer) {
		super();
		this.voteId = voteId;
		this.response = response;
		this.upVote = upVote;
		this.downVote = downVote;
		this.developer = developer;
	}
	public VoteDto()  {
		// TODO Auto-generated constructor stub
	}

 
}
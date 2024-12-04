package com.cg.dto;

import com.cg.entity.Post;

public class VoteDto {
	private Integer voteId;
	private Post postId;
	private boolean voteType;
	//private Developer developerWhoVoted;
	public Integer getVoteId() {
		return voteId;
	}
	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}
	public Post getPostId() {
		return postId;
	}
	public void setPostId(Post postId) {
		this.postId = postId;
	}
	public boolean isVoteType() {
		return voteType;
	}
	public void setVoteType(boolean voteType) {
		this.voteType = voteType;
	}
	//	public Developer getDeveloperWhoVoted() {
	//		return developerWhoVoted;
	//	}
	//	public void setDeveloperWhoVoted(Developer developerWhoVoted) {
	//		this.developerWhoVoted = developerWhoVoted;
	//	}



}
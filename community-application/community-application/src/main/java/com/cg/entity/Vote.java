package com.cg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="vote_table")
public class Vote {
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator_sequence")
	@SequenceGenerator(name="generator_sequence",sequenceName="gen_seq",allocationSize=1)
	private Integer voteId;
	@ManyToOne
	@JoinColumn(name = "postId")
	private Post postId;
	private boolean voteType;
	//	@ManyToOne
	//	@JoinColumn(name = "userId")
	//	private Developer developerWhoVoted;
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
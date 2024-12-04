package com.cg.entity;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	private int voteId;
	@ManyToOne
	@JoinColumn(name = "responseId")
	private Response response;
	private int voteCount;
	//2 variable upvote and downvote
	private int upVote;
	private int downVote;
	@ManyToOne
	@JoinColumn(name = "developerId")
	private Developer developer;

	public int getVoteId() {
		return voteId;
	}
 
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}
 
	public Response getResponse() {
		return response;
	}
 
	public void setResponse(Response response) {
		this.response = response;
	}
 
	public int getVoteCount() {
		return voteCount;
	}
 
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
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
 
	public Developer getDeveloper() {
		return developer;
	}
 
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
 
	public Vote(int voteId, Response response, int upVote, int downVote, Developer developer) {
		super();
		this.voteId = voteId;
		this.response = response;
		this.upVote = upVote;
		this.downVote = downVote;
		this.developer = developer;
	}
	public Vote()  {
		// TODO Auto-generated constructor stub
	}
}
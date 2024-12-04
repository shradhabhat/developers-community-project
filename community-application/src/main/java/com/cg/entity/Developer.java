package com.cg.entity;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name = "developer_table")
public class Developer{
	@Id
	private int developerId;
	@Column(length = 20, unique = true)
	private String userName;
	@Column(length=30, unique=true)
	@NotBlank(message = "Please enter email address")
	@NotNull(message = "Please enter email address")
	@Email(message = "Invalid email address")
	private String emailAddress;
	@Column(length = 40, unique=true)
	@NotBlank(message = "Please enter github link")
	@NotNull(message = "Please enter github link")
	private String githubLink;
	private int reputationPoints;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate memberSince;


	public Developer() {
		super();
	}
	
	public Developer(int developerId,
			@NotBlank(message = "Please enter username") @NotNull(message = "Please enter username") String userName,
			@NotBlank(message = "Please enter email address") @NotNull(message = "Please enter email address") @Email(message = "Invalid email address") String emailAddress,
			@NotBlank(message = "Please enter github link") @NotNull(message = "Please enter github link") String githubLink,
			int reputationPoints, LocalDate memberSince) {
		super();
		this.developerId = developerId;
		this.userName = userName;
		this.emailAddress = emailAddress;
		this.githubLink = githubLink;
		this.reputationPoints = reputationPoints;
		this.memberSince = memberSince;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public int getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}
	public String getGithubLink() {
		return githubLink;
	}
	public void setGithubLink(String githubLink) {
		this.githubLink = githubLink;
	}
	public int getReputationPoints() {
		return reputationPoints;
	}
	public void setReputationPoints(int reputationPoints) {
		this.reputationPoints = reputationPoints;
	}
	public LocalDate getMemberSince() {
		return memberSince;
	}
	public void setMemberSince(LocalDate memberSince) {
		this.memberSince = memberSince;
	}

}

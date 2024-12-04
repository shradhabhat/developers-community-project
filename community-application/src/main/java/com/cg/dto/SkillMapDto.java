package com.cg.dto;
 
public class SkillMapDto {
	private String profeciencyLevel;
	private String experienceLevel;
	private String projects;
	private int developerId;
	private Long skillId;

	public SkillMapDto() {
		super();
	}
	public SkillMapDto(String profeciencyLevel, String experienceLevel, String projects,
			int developerId, Long skillId) {
		super();
		this.profeciencyLevel = profeciencyLevel;
		this.experienceLevel = experienceLevel;
		this.projects = projects;
		this.developerId = developerId;
		this.skillId = skillId;
	}
	public String getProfeciencyLevel() {
		return profeciencyLevel;
	}
	public void setProfeciencyLevel(String profeciencyLevel) {
		this.profeciencyLevel = profeciencyLevel;
	}
	public String getExperienceLevel() {
		return experienceLevel;
	}
	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}
	public String getProjects() {
		return projects;
	}
	public void setProjects(String projects) {
		this.projects = projects;
	}
	public Integer getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(Integer developerId) {
		this.developerId = developerId;
	}
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
 
}
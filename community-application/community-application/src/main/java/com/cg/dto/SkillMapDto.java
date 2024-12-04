package com.cg.dto;

public class SkillMapDto {
	private String skillDescription;
	private String profeciencyLevel;
	private String experienceLevel;
	private String projects;
	private int developerId;
	private Long skillId;
	
	
	
	public SkillMapDto() {
		super();
	}
	public SkillMapDto(String skillDescription, String profeciencyLevel, String experienceLevel, String projects,
			int developerId, Long skillId) {
		super();
		this.skillDescription = skillDescription;
		this.profeciencyLevel = profeciencyLevel;
		this.experienceLevel = experienceLevel;
		this.projects = projects;
		this.developerId = developerId;
		this.skillId = skillId;
	}
	public String getSkillDescription() {
		return skillDescription;
	}
	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
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
package com.cg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "skillmap_table")
public class SkillMap {

	@Column(length=40)
	@NotBlank(message = "Please enter Skill description")
	@NotNull(message = "Please enter Skill description")
	private String skillDescription;
	@Column(length=15 ,nullable=false)
	@NotBlank(message = "Please enter Proficiency Level")
	@NotNull(message = "Please enter Proficiency Level")
	private String profeciencyLevel;
	@Column(length = 15, nullable=false)
	@NotBlank(message = "Please enter Experience Level")
	@NotNull(message = "Please enter Experience Level")
	private String experienceLevel;
	@Column(length=50)
	@NotBlank(message = "Please enter Project")
	@NotNull(message = "Please enter Project")
	private String projects;
	@EmbeddedId
	private SkillDetailId skillDetailId;

	public SkillMap(
			@NotBlank(message = "Please enter Skill description") @NotNull(message = "Please enter Skill description") String skillDescription,
			@NotBlank(message = "Please enter Proficiency Level") @NotNull(message = "Please enter Proficiency Level") String profeciencyLevel,
			@NotBlank(message = "Please enter Experience Level") @NotNull(message = "Please enter Experience Level") String experienceLevel,
			@NotBlank(message = "Please enter Project") @NotNull(message = "Please enter Project") String projects,
			SkillDetailId skillDetailId) {
		super();
		this.skillDescription = skillDescription;
		this.profeciencyLevel = profeciencyLevel;
		this.experienceLevel = experienceLevel;
		this.projects = projects;
		this.skillDetailId = skillDetailId;
	}

	public SkillMap() {
		super();
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
	public SkillDetailId getSkillDetailId() {
		return skillDetailId;
	}
	public void setSkillDetailId(SkillDetailId skillDetailId) {
		this.skillDetailId = skillDetailId;
	}



}


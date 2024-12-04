package com.cg.dto;
 
public class SkillDto {
	private Long skillId;
	private String skillName;
	private String skillDescription;
	public String getSkillDescription() {
		return skillDescription;
	}
	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}
	private String dependecySkill;
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getDependecySkill() {
		return dependecySkill;
	}
	public void setDependecySkill(String dependecySkill) {
		this.dependecySkill = dependecySkill;
	}
 
	public SkillDto(Long skillId, String skillName, String skillDescription, String dependecySkill) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
		this.skillDescription = skillDescription;
		this.dependecySkill = dependecySkill;
	}
 
}
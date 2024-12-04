package com.cg.dto;

public class SkillDto {
	private Long skillId;
	private String skillName;
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


}
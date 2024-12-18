package com.cg.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
//@IdClass(value = SkillDetailId.class)
public class SkillDetailId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne @JoinColumn(name = "skillId")
	private Skill skill;
	@ManyToOne @JoinColumn(name="DeveloperId")
	private Developer developer;
	
	public SkillDetailId(Skill skill, Developer developer) {
		super();
		this.skill = skill;
		this.developer = developer;
	}
	public SkillDetailId() {
		super();
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public Developer getDeveloper() {
		return developer;
	}
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

}

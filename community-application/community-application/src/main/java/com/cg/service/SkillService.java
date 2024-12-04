package com.cg.service;

import java.util.List;

import com.cg.entity.Skill;


public interface SkillService {
	public Skill addSkill(Skill skill);
	public List<Skill> getAllSkills();
	public Skill getSkillById(Long skillId);
	public Skill updateSkill(Long skillId,Skill updatedSkill);
	public void deleteSkill(Long skillId);
}
package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Skill;
import com.cg.exception.ApplicationException;
import com.cg.repository.SkillRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SkillServiceImplementation implements SkillService{
	@Autowired
	private SkillRepository skillRepository;

	@Override
	public Skill addSkill(Skill skill) {
		skill.setSkillName(skill.getSkillName().toLowerCase());
		return skillRepository.save(skill);
	}

	@Override
	public List<Skill> getAllSkills() {
		return skillRepository.findAll();
	}

	@Override
	public Skill getSkillById(Long skillId) {
		return skillRepository.findById(skillId).orElseThrow(()->new ApplicationException("Skill Not found"));
	}

	@Override
	public Skill updateSkill(Long skillId, Skill updatedSkill) {
		Skill skill= skillRepository.findById(skillId).orElseThrow(()->new ApplicationException("Skill not found"));
		skill.setSkillName(updatedSkill.getSkillName().toLowerCase());
		skill.setDependencySkill(updatedSkill.getDependencySkill());
		return skillRepository.save(skill);
	}

	@Override
	public void deleteSkill(Long skillId) {
		if(!skillRepository.existsById(skillId)) {
			throw new ApplicationException("Skill not found");
		}
		skillRepository.deleteById(skillId);	
	}
}
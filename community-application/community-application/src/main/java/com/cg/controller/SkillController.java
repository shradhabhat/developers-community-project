package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Skill;
import com.cg.service.SkillService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/developers/skill")
public class SkillController {
	@Autowired
	private SkillService skillService;
	@PostMapping
	public Skill addSkill(@Valid @RequestBody Skill skill) {
		return skillService.addSkill(skill);
	}
	@GetMapping
	public List<Skill> getAllSkills(){
		return skillService.getAllSkills();
	}
	@GetMapping("/{id}")
	public Skill getSkillById(@Valid @PathVariable("id") Long skillId) {
		return skillService.getSkillById(skillId);
	}
	@PutMapping("/{id}")
	public Skill updateSkill(@Valid @PathVariable("id") Long skillId, @RequestBody Skill updatedSkill) {
		return skillService.updateSkill(skillId,updatedSkill);
	}
	@DeleteMapping("/{id}")
	public void deleteSkill(@Valid @PathVariable("id") Long skillId) {
		skillService.deleteSkill(skillId);
	}

}

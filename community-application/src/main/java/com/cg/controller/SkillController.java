package com.cg.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Skill> addSkill(@Valid @RequestBody Skill skill) {
		skillService.addSkill(skill);
		return ResponseEntity.status(HttpStatus.CREATED).body(skill);
	}
	@GetMapping
	public ResponseEntity<List<Skill>> getAllSkills(){
		List<Skill> skillList= skillService.getAllSkills();
		return ResponseEntity.ok().body(skillList);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Skill> getSkillById(@Valid @PathVariable("id") Long skillId) {
		Skill skill = skillService.getSkillById(skillId);
		return ResponseEntity.ok().body(skill);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Skill> updateSkill(@Valid @PathVariable("id") Long skillId, @RequestBody Skill updatedSkill) {
		Skill skill=  skillService.updateSkill(skillId,updatedSkill);
		return ResponseEntity.ok().body(skill);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSkill(@Valid @PathVariable("id") Long skillId) {
		skillService.deleteSkill(skillId);
		return ResponseEntity.noContent().build();
	}
}
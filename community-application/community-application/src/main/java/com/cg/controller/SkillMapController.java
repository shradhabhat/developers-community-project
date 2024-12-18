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

import com.cg.dto.SkillMapDto;
import com.cg.entity.Developer;
import com.cg.entity.Skill;
import com.cg.entity.SkillMap;
import com.cg.service.SkillMapService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/skillMap")
public class SkillMapController {
	@Autowired
	private SkillMapService skillMapService;
	@PostMapping
	public void postSkillsDeveloper(@Valid   @RequestBody  SkillMapDto skillMapDto) {
		skillMapService.addSkillMap(skillMapDto);
	}
	@GetMapping
	public List<SkillMap> getAllSkillMaps(){
		return skillMapService.getAllSkillMaps();
	}

	@GetMapping("/{skillId}/{developerId}")
	public SkillMap getSkillMapById(@Valid @PathVariable("skillId") Long skillId, @PathVariable("developerId") int developerId) {
		return skillMapService.getSkillMapById(skillId, developerId);
	}
	@PutMapping("/{skillId}/{developerId}")
	public SkillMap updateSkillMap(@Valid @PathVariable("skillId") Long skillId, @PathVariable("developerId") int developerId, @RequestBody SkillMapDto skillMapDto ) {
		return skillMapService.updateSkillMap(skillId, developerId, skillMapDto);
	}
	@DeleteMapping("/{skillId}/{developerId}")
	public void deleteSkillMap(@Valid @PathVariable("skillId") Long skillId, @PathVariable("developerId") int developerId) {
		skillMapService.deleteSkillMap(skillId, developerId);
	}

	@GetMapping("/developer/{developerId}")
	public List<SkillMap> findByDeveloperId(@Valid @PathVariable("developerId") int developerId) {
		return skillMapService.findByDeveloperId(developerId);
	}

	@GetMapping("/skill/{skillId}")
	public List<SkillMap> findBySkillId(@Valid  @PathVariable("skillId") Long skillId) {
		return skillMapService.findBySkillId(skillId);
	}
	@GetMapping("/skills/{developerId}")
	public List<Skill> findSkillByDeveloperId(@PathVariable("developerId") int developerId){
		List<Skill> skill =skillMapService.findSkillByDeveloperId(developerId);
		return skill;
	}
	@GetMapping("/allDevelopers/{skillId}")
	public List<Developer> findDeveloperBySkillId(@PathVariable("skillId") Long skillId){
		List<Developer> developerList = skillMapService.findDeveloperBySkillId(skillId); 
		return developerList;
	}

}
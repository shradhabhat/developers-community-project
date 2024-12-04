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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.cg.dto.SkillMapDto;
 
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
	public ResponseEntity<Void> postSkillsDeveloper(@Valid   @RequestBody  SkillMapDto skillMapDto,@RequestHeader("loggedInUser") int userId) {
		skillMapDto.setDeveloperId(userId);
		  skillMapService.addSkillMap(skillMapDto);
		  return ResponseEntity.noContent().build();
 
	}
	//to get all skills
	@GetMapping
	public ResponseEntity<List<SkillMap>> getAllSkillMaps(@RequestHeader("loggedInUser") int devId,@RequestHeader("loggedInUser") int userId){
		List<SkillMap> skillMapList = skillMapService.getAllSkillMaps();
		return ResponseEntity.status(HttpStatus.CREATED).body(skillMapList);
	}
	//to get the skillMap based on both the ids
	@GetMapping("/{skillId}")
	public ResponseEntity<SkillMap> getSkillMapById(@Valid @PathVariable("skillId") Long skillId,@RequestHeader("loggedInUser") int userId ) {
		SkillMap skillMap=skillMapService.getSkillMapById(skillId, userId);
		return ResponseEntity.ok().body(skillMap);
	}
	//to update a particular skillMap
	@PutMapping("/{skillId}/{developerId}")
	public ResponseEntity<SkillMap> updateSkillMap(@Valid @PathVariable("skillId") Long skillId, @PathVariable("developerId") int developerId, @RequestBody SkillMapDto skillMapDto ,@RequestHeader("loggedInUser") int userId) {
		SkillMap skillMap = skillMapService.updateSkillMap(skillId, developerId, skillMapDto);
		return ResponseEntity.ok().body(skillMap);
	}
	//to delete a particular skillMap
	@DeleteMapping("/delete/{skillId}")
	public ResponseEntity<Void> deleteSkillMap(@Valid @PathVariable("skillId") Long skillId,@RequestHeader("loggedInUser") int devId) {
		skillMapService.deleteSkillMap(skillId, devId);
		return ResponseEntity.noContent().build();
	}
	//to get the skillMaps of the user who is logged in
	@GetMapping("/developer")
	public ResponseEntity<List<SkillMap>> findByDeveloperId(@Valid @RequestHeader("loggedInUser") int devId) {
		List<SkillMap> skillMapList= skillMapService.findByDeveloperId(devId);
		return ResponseEntity.ok().body(skillMapList);
	}
	//to get the skillMap of a particular developer
	@GetMapping("/developer/{developerId}")
	public ResponseEntity<List<SkillMap>> findByDeveloperIdAll(@Valid @PathVariable("developerId") int developerId ,@RequestHeader("loggedInUser") int devId) {
		List<SkillMap> skillMapList= skillMapService.findByDeveloperId(developerId);
		return ResponseEntity.ok().body(skillMapList);
	}
	//to get the skillMap based on the skillId given 
	@GetMapping("/skill/{skillId}")
	public ResponseEntity<List<SkillMap>> findBySkillId(@Valid  @PathVariable("skillId") Long skillId) {
		List<SkillMap> skillMapList =skillMapService.findBySkillId(skillId);
		return ResponseEntity.ok().body(skillMapList);
	}
	//to get the skills of the logged in user
	@GetMapping("/skills")
	public ResponseEntity<List<Skill>> findSkillByDeveloperId(@RequestHeader("loggedInUser") int userId){
		System.out.println(userId);
		System.out.println(userId);
		System.out.println(userId);
		List<Skill> skill =skillMapService.findSkillByDeveloperId(userId);
		return ResponseEntity.ok().body(skill);
	}
	//to get the skills of a particular developer
	@GetMapping("/skills/{devId}")
	public ResponseEntity<List<Skill>> findSkillByOtherDeveloperId(@Valid @PathVariable("devId") int developerId){
		List<Skill> skill =skillMapService.findSkillByDeveloperId(developerId);
		return ResponseEntity.ok().body(skill);
	}
//	//to get all the developers who have that particular skill
//	@GetMapping("/allDevelopers/{skillId}")
//	public List<Developer> findDeveloperBySkillId(@PathVariable("skillId") Long skillId){
//		List<Developer> developerList = skillMapService.findDeveloperBySkillId(skillId); 
//		return developerList;
//	}
//	
	//to update skill map for the logged in developer
	@PutMapping("/update/{skillId}")
	public ResponseEntity<SkillMap> updateDevSkillMap(@Valid @PathVariable("skillId") Long skillId, @RequestBody SkillMapDto skillMapDto ,@RequestHeader("loggedInUser") int userId) {
		 SkillMap skillMap =skillMapService.updateSkillMap(skillId, userId, skillMapDto);
		 return ResponseEntity.ok().body(skillMap);
	}
 
}
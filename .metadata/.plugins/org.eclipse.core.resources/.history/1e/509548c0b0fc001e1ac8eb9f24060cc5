package com.cg.controller;
import java.time.LocalDate;
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
import com.cg.entity.Developer;
import com.cg.service.DeveloperService;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/developers")
public class DeveloperController {
	@Autowired
	private DeveloperService developerService;
	@PostMapping
	public ResponseEntity<Developer> addDeveloperController(@Valid @RequestBody Developer developer,@RequestHeader("loggedInUser") int userId) {
		String now = LocalDate.now().toString();
		developer.setMemberSince(LocalDate.parse(now.replace(" ", "T")));
		developer.setDeveloperId(userId);
		Developer developerCreated= developerService.addDeveloperService(developer);
		return ResponseEntity.status(HttpStatus.CREATED).body(developerCreated);
	}
	@PutMapping("/{devId}")
	public ResponseEntity<Developer> updateDeveloperController(@Valid @RequestBody Developer developer, @PathVariable("devId") int developerId ) {
		Developer updatedDeveloper = developerService.updateDeveloperService(developer, developerId);
		return ResponseEntity.ok().body(updatedDeveloper);
	}
	@GetMapping("/getcurrentdeveloper")
	public ResponseEntity<Developer> getCurrentDeveloperController(@Valid  @RequestHeader("loggedInUser") int userId) {
		Developer developer= developerService.getDeveloperByIdService(userId);
		return ResponseEntity.ok().body(developer);
	}
 

	@GetMapping("/getdeveloperbyid/{devId}")
	public ResponseEntity<Developer> getDeveloperByIdController(@Valid @PathVariable("devId") int developerId) {
		Developer developer = developerService.getDeveloperByIdService(developerId);
		return ResponseEntity.ok().body(developer);
	}
	@GetMapping("/getdeveloperbyreputation/{rep}")
	public ResponseEntity<List<Developer>> getDeveloperByReputationController(@Valid @PathVariable("rep") int reputation) {
		List<Developer> developerList= developerService.getDeveloperByReputationService(reputation);
		return ResponseEntity.ok().body(developerList);
	}
	@GetMapping
	public ResponseEntity<List<Developer>> getAllDevelopersController() {
		List<Developer> developerList=  developerService.getAllDevelopersService();
		return ResponseEntity.ok().body(developerList);
	}
	@GetMapping("/getdevelopersbymaxreputation")
	public ResponseEntity<List<Developer>> getByMaxReputationController() {
		List<Developer> developerList= developerService.getByMaxReputationService();
		return ResponseEntity.ok().body(developerList);
	}
	@GetMapping("/getTopDevelopers")
	public ResponseEntity<List<Developer>> getTopDevelopers() {
		List<Developer> developerList=developerService.getByTopDeveloper();
		return ResponseEntity.ok().body(developerList);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDeveloper(@Valid @PathVariable("id")int developerId) {
		developerService.deleteDeveloper(developerId);
		return ResponseEntity.noContent().build();
	}
}
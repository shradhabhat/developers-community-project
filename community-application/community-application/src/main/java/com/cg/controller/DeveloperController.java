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

import com.cg.entity.Developer;
import com.cg.service.DeveloperService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
	@Autowired
	private DeveloperService developerService;

	@PostMapping
	public Developer addDeveloperController(@Valid @RequestBody Developer developer) {
		return developerService.addDeveloperService(developer);
	}

	@PutMapping("/updatedeveloper/{devId}")
	public Developer updateDeveloperController(@Valid @RequestBody Developer developer, @PathVariable("devId") int developerId ) {
		return developerService.updateDeveloperService(developer, developerId);
	}

	@GetMapping("/getdeveloperbyid/{devId}")
	public Developer getDeveloperByIdController(@Valid @PathVariable("devId") int developerId) {
		return developerService.getDeveloperByIdService(developerId);		
	}

	@GetMapping("/getdeveloperbyreputation/{rep}")
	public List<Developer> getDeveloperByReputationController(@Valid @PathVariable("rep") int reputation) {
		return developerService.getDeveloperByReputationService(reputation);
	}

	@GetMapping("/getalldevelopers")
	public List<Developer> getAllDevelopersController() {
		return developerService.getAllDevelopersService();
	}

	@GetMapping("/getdevelopersbymaxreputation")
	public List<Developer> getByMaxReputationController() {
		return developerService.getByMaxReputationService();
	}

	@DeleteMapping("/deletedevelopers/{id}")
	public void deleteDeveloper(@Valid @PathVariable("id")int developerId) {
		developerService.deleteDeveloper(developerId);
	}

}

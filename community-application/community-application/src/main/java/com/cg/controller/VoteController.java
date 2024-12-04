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

import com.cg.dto.VoteDto;
import com.cg.service.VoteService;

@RestController
@RequestMapping("/votes")
public class VoteController {

	@Autowired
	private VoteService voteService;

	@GetMapping
	public List<VoteDto>getAllVotes()    {
		return voteService.getAllVotes();
	}

	@GetMapping("/{id}")
	public VoteDto getVoteById(@PathVariable("id") int id) {
		return voteService.getVoteById(id);
	}

	@PostMapping
	public VoteDto addVote(@RequestBody VoteDto voteDTO) {
		return voteService.addVote(voteDTO);        
	}

	@PutMapping("/{id}")
	public VoteDto updateVote(@PathVariable("id") int id, @RequestBody VoteDto voteDTO) {
		voteDTO.setVoteId(id);
		return voteService.updateVote(id, voteDTO);
	}


	@DeleteMapping("/{id}")
	public void deleteVote(@PathVariable("id") int id) {
		voteService.deleteVote(id);
	}

}
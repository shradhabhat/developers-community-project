package com.cg.controller;
 
import java.security.Principal;
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
import com.cg.entity.Vote;
import com.cg.service.VoteService;
 
@RestController
@RequestMapping("/votes")
public class VoteController {
 
	@Autowired
	private VoteService voteService;
	@PostMapping
	public Vote addVote(@RequestBody VoteDto voteDto, Principal p) {
		return voteService.addVote(voteDto);        
	}
 
	
	@GetMapping("/getbyresponseid/{responseId}")
	public List<Vote> getVoteByResponse(@PathVariable("responseId") int responseId){
		return voteService.getVoteByResponse(responseId);
	}
	
 
	
 
	
	@PutMapping("/updatevote/{voteId}")
	public VoteDto updateVote(@PathVariable("voteId") int voteId, @RequestBody VoteDto voteDto, Principal p) {
		voteService.updateVote(voteDto,voteId);
		return voteDto;
	}
	

 
 
	
 
}
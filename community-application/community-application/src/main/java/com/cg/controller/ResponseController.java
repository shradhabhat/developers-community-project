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

import com.cg.dto.ResponseDto;
import com.cg.entity.Response;
import com.cg.service.ResponseService;

@RestController
@RequestMapping("/responses")
public class ResponseController {
	@Autowired
	private ResponseService responseService;

	@PostMapping
	public Response addResponse(@RequestBody ResponseDto responseDto,Principal p) {
		return responseService.addResponse(responseDto);      

	}
	@GetMapping("/getbyresponseid/{responseId}")
	public Response getResponseByResponseId(@PathVariable("responseId") int responseId) {
		return responseService.getResponseByResponseId(responseId);
	}
	@GetMapping("/getbypostid/{postId}")
	public List<Response> getResponseByPost(@PathVariable("postId") int postId){
		return responseService.getResponseByPost(postId);
	}
	@GetMapping("/getbydeveloperid/{developerId}")
	public List<Response> getResponseByDeveloper(@PathVariable("developerId") int developerId){
		return responseService.getResponseByDeveloper(developerId);
	}
	@DeleteMapping("/{responseId}")
	public void removeResponseById(@PathVariable("responseId") int responseId) {
		responseService.removeResponse(responseId);
	}

	@PutMapping("/updateresponse/{responseId}")
	public ResponseDto updateResponse(@PathVariable("responseId") int responseId,@RequestBody ResponseDto responseDto,Principal p) {   	 
		responseService.updateResponse(responseDto,responseId);      
		return responseDto;
	}

}

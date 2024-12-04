package com.cg.controller;
 
import java.time.LocalDateTime;
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
 
import com.cg.dto.ResponseDto;
import com.cg.entity.Response;
import com.cg.service.ResponseService;
 
@RestController
@RequestMapping("/responses")
public class ResponseController {
	@Autowired
	private ResponseService responseService;
 
	@PostMapping("/addresponse/{postId}")
	public ResponseEntity<Response> addResponse(@RequestBody ResponseDto responseDto,@PathVariable("postId") int postId,@RequestHeader("loggedInUser") int devId) {
		//responseDto.setDeveloperId(responseService.findDeveloperByUserName(userName).getDeveloperId()); 
	    String now =LocalDateTime.now().toString();
		responseDto.setDeveloperId(devId);
		responseDto.setPostId(postId);
		responseDto.setRespDateTime(LocalDateTime.parse(now.replace( " " , "T" )));
		Response response=responseService.addResponse(responseDto);    
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@GetMapping("/getbyresponseid/{responseId}")
	public ResponseEntity<Response>  getResponseByResponseId(@PathVariable("responseId") int responseId) {
		Response response=responseService.getResponseByResponseId(responseId);
		return ResponseEntity.ok().body(response);
	}
	@GetMapping("/getbypostid/{postId}")
	public ResponseEntity<List<Response>> getResponseByPost(@PathVariable("postId") int postId){
		List<Response> responseList=responseService.getResponseByPost(postId);
		return ResponseEntity.ok().body(responseList);
	}
	@GetMapping("/getbydeveloperid/{developerId}")
	public ResponseEntity<List<Response>> getResponseByDeveloper(@PathVariable("developerId") int developerId){
		List<Response> responseList=responseService.getResponseByDeveloper(developerId);
		return ResponseEntity.ok().body(responseList);
	}
	@DeleteMapping("/{responseId}")
	public ResponseEntity<Void> removeResponseById(@PathVariable("responseId") int responseId,@RequestHeader("loggedInUser") int devId) {
		responseService.removeResponse(responseId,devId);
		return ResponseEntity.noContent().build();

	}
 
	@PutMapping("/updateresponse/{responseId}")
	public ResponseEntity<Response> updateResponse(@PathVariable("responseId") int responseId,@RequestBody ResponseDto responseDto,@RequestHeader("loggedInUser") int devId) {   	 
		responseDto.setDeveloperId(devId);
		String now =LocalDateTime.now().toString();
		responseDto.setRespDateTime(LocalDateTime.parse(now.replace( " " , "T" )));
		Response response=responseService.updateResponse(responseDto,responseId);   
		return ResponseEntity.ok().body(response);
	}

 
}
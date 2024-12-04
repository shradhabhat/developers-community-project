package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.ResponseDto;
import com.cg.entity.Developer;
import com.cg.entity.Post;
import com.cg.entity.Response;
import com.cg.exception.ApplicationException;
import com.cg.repository.DeveloperRepository;
import com.cg.repository.PostRepository;
import com.cg.repository.ResponseRepository;

import jakarta.validation.Valid;
@Service
public class ResponseServiceImplementation implements ResponseService{
	@Autowired
	private ResponseRepository responseRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private DeveloperRepository developerRepository;

	@Override
	public Response  addResponse(@Valid ResponseDto  responseDto) {
		Post post=postRepository.findById(responseDto.getPostId()).orElseThrow(()->new ApplicationException("Post not found"));
		Developer developer=developerRepository.findById(responseDto.getDeveloperId()).orElseThrow(()->new ApplicationException("Developer not found"));
		Response response=new Response();
		response.setPost(post);
		response.setDeveloper(developer);
		response.setAnswer(responseDto.getAnswer());
		response.setRespDateTime(responseDto.getRespDateTime());
		responseRepository.save(response);
		return response;
	}
	
	
	
	public void  removeResponse(@Valid int respId,int devId) {
		Response response=responseRepository.findById(respId).orElseThrow(()->new ApplicationException("Response not found, cannot remove response"));
		if(response.getDeveloper().getDeveloperId()!=devId) {
			throw new ApplicationException("Wrong developer!");
		}
		responseRepository.deleteById(respId);
	}

	@Override
	public List<Response> getResponseByPost(@Valid int postId) {
		
		if(postRepository.findById(postId).isEmpty()) {
			throw new ApplicationException("Post not found");
		}else {
			return responseRepository.searchByPostId(postId);

		}
		
	}
	@Override
	public List<Response> getResponseByDeveloper(@Valid int developerId) {
		
		if(developerRepository.findById(developerId).isEmpty()) {
			throw new ApplicationException("Developer not found, cannot get response");
		}else {
			return responseRepository.searchByDeveloperId(developerId);
		}
		
	}

	@Override
		public Response  updateResponse(@Valid ResponseDto  responseDto,int responseId) {
			Response response=responseRepository.findById(responseId).orElseThrow(()->new ApplicationException("Response not found, cannot update response"));
			if(response.getDeveloper().getDeveloperId()!=responseDto.getDeveloperId()) {
				throw new ApplicationException("Wrong developer!");
			}
			response.setAnswer(responseDto.getAnswer());
			response.setRespDateTime(responseDto.getRespDateTime());
			responseRepository.save(response);
			 
			return response;
		}
	@Override
	public Response getResponseByResponseId(int responseId){
		return responseRepository.findById(responseId).orElseThrow(()->new ApplicationException("Response not found, cannot get response"));
		
	}
}

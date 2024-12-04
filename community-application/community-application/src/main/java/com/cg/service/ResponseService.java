package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.dto.ResponseDto;
import com.cg.entity.Response;
import com.cg.exception.ApplicationException;
@Service
public interface ResponseService {
	Response  addResponse(ResponseDto  responseDTO) throws ApplicationException;
	Response getResponseByResponseId(int responseId) throws ApplicationException;
	List<Response> getResponseByPost(int postId) throws ApplicationException;
	List<Response> getResponseByDeveloper(int developerId) throws ApplicationException;
	void  removeResponse(int respId) throws ApplicationException;

	ResponseDto  updateResponse(ResponseDto  responseDTO,int responseId) throws ApplicationException;

}

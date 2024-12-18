package com.cg.service;
 
import java.util.List;

import com.cg.dto.VoteDto;
import com.cg.entity.Vote;
import com.cg.exception.ApplicationException;
 
public interface VoteService {
 
	Vote addVote(VoteDto voteDto)throws ApplicationException;
 
	List<Vote> getVoteByResponse(int responseId)throws ApplicationException;
	VoteDto updateVote(VoteDto voteDto,int voteId)throws ApplicationException;

 
 
	

}


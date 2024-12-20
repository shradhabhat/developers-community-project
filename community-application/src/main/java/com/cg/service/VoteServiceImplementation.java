package com.cg.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.cg.dto.VoteDto;
import com.cg.entity.Developer;
import com.cg.entity.Response;
import com.cg.entity.Vote;
import com.cg.exception.ApplicationException;
import com.cg.repository.DeveloperRepository;
import com.cg.repository.ResponseRepository;
import com.cg.repository.VoteRepository;
 
@Service
public class VoteServiceImplementation implements VoteService {
 
	@Autowired
	private VoteRepository voteRepository;
	@Autowired
	private ResponseRepository responseRepository;
	@Autowired
	private DeveloperRepository developerRepository;
 
	@Override
	public Vote addVote(VoteDto voteDto) {
		Response response=responseRepository.findById(voteDto.getResponse()).orElseThrow(()->new ApplicationException("Response not found"));
		Developer developer=developerRepository.findById(voteDto.getDeveloper()).orElseThrow(()->new ApplicationException("Developer not found"));
		Vote vote = new Vote();
		vote.setResponse(response);
		vote.setDeveloper(developer);
//		vote.setDownVote(voteDto.getDownVote());
//		vote.setUpVote(voteDto.getUpVote());
		vote.setVoteCount(voteDto.getVoteCount());
		voteRepository.save(vote);
		return vote;
	}
 
	
 
	@Override
	public List<Vote> getVoteByResponse(int responseId)  {
		if(responseRepository.findById(responseId).isEmpty()) {
			throw new ApplicationException("Response not found");
		}
		else
		return voteRepository.searchByResponseId(responseId);
	}
 
	
 
	
 
	
 
	@Override
	public VoteDto updateVote(VoteDto voteDto, int voteId) {
		Vote vote = voteRepository.findById(voteId).orElseThrow(()->new ApplicationException("Vote not found"));
		//vote.setVoteType(voteDto.isVoteType());
		voteRepository.save(vote);
		return voteDto;
	}



	
 
 
	
 
 
}
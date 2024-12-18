package com.cg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.VoteDto;
import com.cg.entity.Vote;
import com.cg.repository.VoteRepository;

@Service
public class VoteServiceImplementation implements VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Override
	public VoteDto addVote(VoteDto voteDTO) {
		Vote vote = new Vote();
		vote.setPostId(voteDTO.getPostId());
		vote.setVoteType(voteDTO.isVoteType());
		//vote.setDeveloperWhoVoted(voteDTO.getDeveloperWhoVoted());

		vote = voteRepository.save(vote);

		voteDTO.setVoteId(vote.getVoteId());
		voteDTO.setPostId(vote.getPostId());
		voteDTO.setVoteType(vote.isVoteType());
		//voteDTO.setDeveloperWhoVoted(vote.getDeveloperWhoVoted());

		return voteDTO;

	}

	@Override
	public VoteDto updateVote(Integer id, VoteDto voteDTO) {
		Vote vote = voteRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));

		vote.setPostId(voteDTO.getPostId());
		vote.setVoteType(voteDTO.isVoteType());
		//vote.setDeveloperWhoVoted(voteDTO.getDeveloperWhoVoted());

		vote = voteRepository.save(vote);

		voteDTO.setVoteId(vote.getVoteId());
		voteDTO.setPostId(vote.getPostId());
		voteDTO.setVoteType(vote.isVoteType());
		//voteDTO.setDeveloperWhoVoted(vote.getDeveloperWhoVoted());

		return voteDTO;

	}

	@Override
	public VoteDto getVoteById(Integer voteId) {
		Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new RuntimeException("Post not found"));

		VoteDto voteDTO = new VoteDto();
		voteDTO.setVoteId(vote.getVoteId());
		voteDTO.setPostId(vote.getPostId());
		voteDTO.setVoteType(vote.isVoteType());
		// voteDTO.setDeveloperWhoVoted(vote.getDeveloperWhoVoted());

		return voteDTO;
	}

	@Override
	public List<VoteDto> getAllVotes() {
		List<Vote> votes = voteRepository.findAll();
		List<VoteDto> voteDTOs = new ArrayList<>();

		for (Vote vote : votes) {
			VoteDto voteDTO = new VoteDto();

			voteDTO.setVoteId(vote.getVoteId());
			voteDTO.setVoteType(vote.isVoteType());
			voteDTO.setPostId(vote.getPostId());
			//voteDTO.setDeveloperWhoVoted(vote.getDeveloperWhoVoted());

			voteDTOs.add(voteDTO);
		}
		return voteDTOs;
	}

	@Override
	public void deleteVote(Integer voteId) {
		voteRepository.findById(voteId).get();
		voteRepository.deleteById(voteId);
		return;
	}


}

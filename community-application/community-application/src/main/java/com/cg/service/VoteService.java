package com.cg.service;

import java.util.List;

import com.cg.dto.VoteDto;

public interface VoteService {

	VoteDto addVote(VoteDto voteDto);

	VoteDto updateVote(Integer id, VoteDto voteDto);

	void deleteVote(Integer voteId);

	VoteDto getVoteById(Integer voteId);

	List<VoteDto> getAllVotes();

}

package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.dto.PostDto;
import com.cg.entity.Post;

@Service
public interface PostService {
	Post addPost(PostDto postDto);

	Post updatePost(Integer id, PostDto postDto);

	List<Post> getPostById(Integer postId);
	
	//Post getPostById(Integer postId);
	
	//PostDTO getPostByDeveloperId(Integer developerId);

	void deletePost(Integer postId);
	
	List<Post> getAllPosts();

	List<Post> getPostByDeveloperId(int developerId);
	
	//Integer getNoOfVotesOnPostByVoteType(String voteType, Integer postId);

	//List<Post> getPostsByKeyword(String keyword);

	//List<Post> getPostsByTopic(String topic);
	
	//List<Post> getPostsByDate(LocalDate date);

}

package com.cg.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.PostDto;
import com.cg.entity.Developer;
import com.cg.entity.Post;
import com.cg.exception.ApplicationException;
import com.cg.repository.DeveloperRepository;
import com.cg.repository.PostRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class PostServiceImplementation implements PostService
{
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private DeveloperRepository developerRepository;

	@Override
	public Post addPost(PostDto postDTO) {
		
		Developer developer = developerRepository.findById(postDTO.getDeveloperId()).orElseThrow(()->new ApplicationException("Developer not found"));
		Post post = new Post();
		post.setQuery(postDTO.getQuery());
		post.setPostDateTime(postDTO.getPostDateTime());
		post.setTopic(postDTO.getTopic());
		post.setNoOfViews(postDTO.getNoOfViews());
		post.setDeveloper(developer);
		return postRepository.save(post);

	}


	@Override
    public Post updatePost(Integer id, PostDto postDTO) {
        Post existingPost = postRepository.findById(id).orElseThrow(() -> new RuntimeException("No information on this postId"));
      System.out.println(existingPost.getPostId());
      System.out.println(postDTO.getPostId());
        BeanUtils.copyProperties(postDTO,existingPost);
      // existingPost.setPostId(0);
        Post updatedPost=postRepository.save(existingPost);
        return updatedPost;
 
    }


	@Override
	public List<Post> getPostById(Integer postId) {
		return postRepository.searchByPostId(postId);


	}
	@Override
	public List<Post> getPostByDeveloperId(int developerId) {
		return postRepository.searchByDeveloperId(developerId);

	}

	@Override
	public void deletePost(Integer postId) {
		postRepository.deleteById(postId);

	}

	@Override
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}




}

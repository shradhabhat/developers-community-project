package com.cg.service;
 
import java.util.List;
 
import org.springframework.stereotype.Service;
 
import com.cg.dto.PostDto;
import com.cg.entity.Post;
import com.cg.exception.ApplicationException;
 
@Service
public interface PostService {
	Post addPost(PostDto postDto);
 
	Post updatePost(Integer id, PostDto postDto);
 
	Post getPostById(Integer postId);
	
 
	void deletePost(Integer postId);
	List<Post> getAllPosts();
 
	List<Post> getPostByDeveloperId(int developerId);
	
	List<Post> searchPost(String keyword)throws ApplicationException;

 
}
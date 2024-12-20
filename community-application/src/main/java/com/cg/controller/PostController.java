package com.cg.controller;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.dto.PostDto;
import com.cg.entity.Post;
import com.cg.service.PostService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
    private PostService postService;
 
	@PostMapping
    public Post addPost(@RequestBody PostDto postDto,@RequestHeader("loggedInUser") int devId) {
		String now =LocalDateTime.now().toString();

				postDto.setDeveloperId(devId);
				postDto.setPostDateTime(LocalDateTime.parse(now.replace( " " , "T" )));
				return postService.addPost(postDto);     
    }
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    @GetMapping("/getbypostid/{postId}")
    public Post getPostById(@PathVariable("postId") int postId) {
        return postService.getPostById(postId);
    }
    @GetMapping("/getbydeveloperid/{developerid}")
    public List<Post>getPostByDeveloperId(@PathVariable("developerid") int developerid) {
        return postService.getPostByDeveloperId(developerid);
    }
    @PutMapping("/{postId}")
    public Post updatePost(@Valid @PathVariable("postId") int postId, @RequestBody PostDto postDto,@RequestHeader("loggedInUser") String userName) {
        return postService.updatePost(postId, postDto);
    }
    
    @GetMapping("searchpost/{keyword}")
    public List<Post> getPostsByKeyword(@PathVariable  ("keyword") String keyword) {
        return postService.searchPost(keyword);
    }
 
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable("postId") int postId,@RequestHeader("loggedInUser") String userName) {
    	postService.deletePost(postId);
    }
}
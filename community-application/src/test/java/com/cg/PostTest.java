package com.cg;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
 
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import com.cg.entity.Developer;
import com.cg.entity.Post;
import com.cg.dto.PostDto;
import com.cg.exception.ApplicationException;
import com.cg.repository.DeveloperRepository;
import com.cg.repository.PostRepository;
import com.cg.service.PostServiceImplementation;
 
public class PostTest {
	@Mock
	private PostRepository postRepository;
	@Mock
	private DeveloperRepository developerRepository;
	@InjectMocks
	private PostServiceImplementation postServiceImpl;
	static List<Developer> mockDevelopers;
	static List<Post> mockPosts;
	@BeforeAll
	public static void initMock() {
	Developer developer1=new Developer(1,"prisha", "prisha@gmail.com", "prisha.gitub.com",20,LocalDate.now());
	Developer developer2=new Developer(2,"anam", "anam@gmail.com", "anam.gitub.com",50,LocalDate.now());
	mockDevelopers=Arrays.asList(developer1,developer2);
	Post post1 =new Post(101,"question1", LocalDateTime.now(), "java", mockDevelopers.get(0),20);
	Post post2 =new Post(102,"question2", LocalDateTime.now(), "java", mockDevelopers.get(1),30);
	mockPosts=Arrays.asList(post1,post2);
	}
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Testcase_for_delete_post")
	void testDeletePost() {
		PostDto postDto = new PostDto(1,"query1",LocalDateTime.now(),"topic1", mockDevelopers.get(0).getDeveloperId(), 123);
		Post post = new Post(1,"query2",LocalDateTime.now(),"topic2", mockDevelopers.get(0), 234);	
		when(developerRepository.findById(1)).thenReturn(Optional.of(mockDevelopers.get(0)));
		when(postRepository.findById(1)).thenReturn(Optional.of(post));
		when(postRepository.save(post)).thenReturn(post);
		Post savedPost=postServiceImpl.addPost(postDto);
		postServiceImpl.deletePost(post.getPostId());
		assertFalse(postRepository.findAll().contains(savedPost));
	}
	@Test
	@DisplayName("Testcase_for_get_post_by_developer")
	void testGetPostByDeveloper() {
		PostDto postDto1 = new PostDto(1,"query1",LocalDateTime.now(),"topic1", mockDevelopers.get(0).getDeveloperId(), 123);
		PostDto postDto2 = new PostDto(2,"query2",LocalDateTime.now(),"topic2", mockDevelopers.get(0).getDeveloperId(), 123);
		PostDto postDto3 = new PostDto(2,"query3",LocalDateTime.now(),"topic3", mockDevelopers.get(0).getDeveloperId(), 123);
		when(developerRepository.findById(1)).thenReturn(Optional.of(mockDevelopers.get(0)));
		when(developerRepository.findById(2)).thenReturn(Optional.of(mockDevelopers.get(1)));
		when(postRepository.searchByDeveloperId(2)).thenReturn((Arrays.asList(mockPosts.get(1))));
		postServiceImpl.addPost(postDto1);
		postServiceImpl.addPost(postDto2);
		postServiceImpl.addPost(postDto3);
		List <Post> postByDeveloper=postServiceImpl.getPostByDeveloperId(2);
 
		assertEquals(postByDeveloper.size(),1);
	}
//	@Test
//	@DisplayName("Testcase_for_update_post")
//	void testUpdatePost() {
//		PostDto postDto = new PostDto(1,"query1",LocalDateTime.now(),"topic1", mockDevelopers.get(0).getDeveloperId(), 123);
// 
//		when(developerRepository.findById(1)).thenReturn(Optional.of(mockDevelopers.get(0)));
//		when(postRepository.findById(1)).thenReturn(Optional.of(mockPosts.get(0)));
//		postServiceImpl.addPost(postDto);
//		System.out.println(postDto.getQuery());
//		postDto.setQuery("Updated Query");
//		PostDto updatedPost =postServiceImpl.updatePost(1,postDto);
//		assertEquals(updatedPost.getQuery(),"Updated Query");
//	}
	@Test
	@DisplayName("Testcase_for_add_post_invalid_developer")
	void testAddPostByInvalidDeveloper() {
		int postId=30;
		PostDto postDto = new PostDto(1,"query1",LocalDateTime.now(),"topic1", mockDevelopers.get(0).getDeveloperId(), 123);
		assertThrows(ApplicationException.class, () -> postServiceImpl.addPost(postDto));
	}
//	@Test
//	@DisplayName("Testcase_for_update_invalid_post")
//	void testUpdateInvalidPost() {
//		PostDto postDto = new PostDto(1,"query1",LocalDateTime.now(),"topic1", mockDevelopers.get(0).getDeveloperId(), 123);
//		when(developerRepository.findById(1)).thenReturn(Optional.of(mockDevelopers.get(0)));
//		when(postRepository.findById(1)).thenReturn(Optional.empty());
//		postServiceImpl.addPost(postDto);
//		assertThrows(ApplicationException.class, () -> postServiceImpl.updatePost(1,postDto));
// 
//	}
//	@Test
//	@DisplayName("Testcase_for_remove_invalid_post")
//	void testRemoveInvalidResponse() {
//		int postId=10;
//		assertThrows(ApplicationException.class, () -> postServiceImpl.deletePost(postId));
// 
//	}
	@Test
	@DisplayName("Testcase_for_add_post")
	void testAddPost() {
		PostDto postDto = new PostDto(1,"query1",LocalDateTime.now(),"topic1", mockDevelopers.get(0).getDeveloperId(), 123);
		Post post = new Post(1,"query2",LocalDateTime.now(),"topic2", mockDevelopers.get(0), 234);
		Post postResp = new Post(1,"query2",LocalDateTime.now(),"topic2", mockDevelopers.get(0), 234);
 
		ArgumentCaptor<Post> postCapture=ArgumentCaptor.forClass(Post.class);
		when(postRepository.save(postCapture.capture())).thenAnswer(invocation->{
			Post savedPost=invocation.getArgument(0);
			savedPost.setPostId(1);
			return savedPost;
		});
		when(developerRepository.findById(1)).thenReturn(Optional.of(mockDevelopers.get(0)));
		when(postRepository.save(post)).thenReturn(postResp);
		Post savedPost=postServiceImpl.addPost(postDto);
		assertNotNull(savedPost);
		assertEquals("query1",savedPost.getQuery());
	}
 
	@Test
	@DisplayName("Testcase_for_search_post_by_keyword")
	void testSearchPostByKeyword() {
		       String keyword = "test";
		    PostDto postDto1 = new PostDto(2,"query2",LocalDateTime.now(),"topic2", mockDevelopers.get(0).getDeveloperId(), 123);
		    PostDto postDto2 = new PostDto(2,"query3",LocalDateTime.now(),"topic3", mockDevelopers.get(0).getDeveloperId(), 123);

 
		        when(postRepository.searchPost(keyword)).thenReturn((Arrays.asList(mockPosts.get(1))));
		     //   postServiceImpl.addPost(postDto1);
				//postServiceImpl.addPost(postDto2);
				List <Post> searchPost=postServiceImpl.searchPost("test");
				assertEquals(searchPost.size(),1);
		    }
 
 
	}
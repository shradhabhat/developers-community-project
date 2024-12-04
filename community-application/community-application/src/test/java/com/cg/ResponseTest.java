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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.cg.dto.ResponseDto;
import com.cg.entity.Developer;
import com.cg.entity.Post;
import com.cg.entity.Response;
import com.cg.exception.ApplicationException;
import com.cg.repository.DeveloperRepository;
import com.cg.repository.PostRepository;
import com.cg.repository.ResponseRepository;
import com.cg.service.ResponseServiceImplementation;
//@SpringBootTest
public class ResponseTest {
	//@MockBean
	@Mock
	private ResponseRepository responseRepository;
	@Mock
	private PostRepository postRepository;
	@Mock
	private DeveloperRepository developerRepository;
	//@Autowired
	@InjectMocks
	private ResponseServiceImplementation responseService;
	static List<Developer> mockDevelopers;
	static List<Post> mockPosts;
	static List<Response> mockResponses;
	@BeforeAll
	public static void initMock() {
		Developer developer1=new Developer(1,"shradha", "shradha@gmail.com", "shradha.gitub.com",20,LocalDate.now());
		Developer developer2=new Developer(2,"akshata", "akshata@gmail.com", "akshata.gitub.com",50,LocalDate.now());
		mockDevelopers=Arrays.asList(developer1,developer2);
		Post post1 =new Post(101,"question1", LocalDateTime.now(), "java", mockDevelopers.get(0),20);
		Post post2 =new Post(102,"question2", LocalDateTime.now(), "java", mockDevelopers.get(1),30);
		mockPosts=Arrays.asList(post1,post2);
		Response response1 =new Response(1,"answer",LocalDateTime.now(), mockPosts.get(0),mockDevelopers.get(0));
		Response response2 =new Response(2,"answer",LocalDateTime.now(), mockPosts.get(0),mockDevelopers.get(1));
		mockResponses=Arrays.asList(response1,response2);
	}
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	@DisplayName("TESTCASE_FOR_ADD_RESPONSE")
	void testAddResponse() {

		ResponseDto responseDto =new ResponseDto(1,"answer",LocalDateTime.now(),mockPosts.get(0).getPostId(),mockDevelopers.get(0).getDeveloperId());
		Response response =new Response(1,"answer",LocalDateTime.now(), mockPosts.get(0),mockDevelopers.get(0));

		when(developerRepository.findById(1)).thenReturn(Optional.of(mockDevelopers.get(0)));
		when(postRepository.findById(101)).thenReturn(Optional.of(mockPosts.get(0)));

		when(responseRepository.save(response)).thenReturn(response);
		Response savedResponse=responseService.addResponse(responseDto);
		assertNotNull(savedResponse);
		assertEquals("answer",savedResponse.getAnswer());
	}
	@Test
	@DisplayName("TESTCASE_FOR_REMOVE_RESPONSE")
	void testRemoveResponse() {
		ResponseDto responseDto =new ResponseDto(1,"answer",LocalDateTime.now(),mockPosts.get(0).getPostId(),mockDevelopers.get(0).getDeveloperId());
		Response response =new Response(1,"answer",LocalDateTime.now(), mockPosts.get(0),mockDevelopers.get(0));

		when(developerRepository.findById(1)).thenReturn(Optional.of(mockDevelopers.get(0)));
		when(postRepository.findById(101)).thenReturn(Optional.of(mockPosts.get(0)));
		when(responseRepository.findById(1)).thenReturn(Optional.of(response));
		when(responseRepository.save(response)).thenReturn(response);
		Response savedResponse=responseService.addResponse(responseDto);
		responseService.removeResponse(response.getResponseId());
		assertFalse(responseRepository.findAll().contains(savedResponse));
	}
	@Test
	@DisplayName("TESTCASE_FOR_GET_RESPONSE_BY_POST")
	void testGetResponseByPost() {
		ResponseDto responseDto1 =new ResponseDto(1,"answer1",LocalDateTime.now(),mockPosts.get(0).getPostId(),mockDevelopers.get(0).getDeveloperId());
		ResponseDto responseDto2 =new ResponseDto(2,"answer2",LocalDateTime.now(),mockPosts.get(0).getPostId(),mockDevelopers.get(1).getDeveloperId());
		ResponseDto responseDto3 =new ResponseDto(2,"answer2",LocalDateTime.now(),mockPosts.get(1).getPostId(),mockDevelopers.get(1).getDeveloperId());

		when(developerRepository.findById(1)).thenReturn(Optional.of(mockDevelopers.get(0)));
		when(developerRepository.findById(2)).thenReturn(Optional.of(mockDevelopers.get(1)));
		when(postRepository.findById(101)).thenReturn(Optional.of(mockPosts.get(0)));
		when(postRepository.findById(102)).thenReturn(Optional.of(mockPosts.get(1)));
		when(responseRepository.searchByPostId(101)).thenReturn(mockResponses);
		responseService.addResponse(responseDto1);
		responseService.addResponse(responseDto2);
		responseService.addResponse(responseDto3);
		List <Response> responsesByPost=responseService.getResponseByPost(101);

		assertEquals(responsesByPost.size(),2);
	}

	@Test
	@DisplayName("TESTCASE_FOR_GET_RESPONSE_BY_DEVELOPER")
	void testGetResponseByDeveloper() {
		ResponseDto responseDto1 =new ResponseDto(1,"answer1",LocalDateTime.now(),mockPosts.get(0).getPostId(),mockDevelopers.get(0).getDeveloperId());
		ResponseDto responseDto2 =new ResponseDto(2,"answer2",LocalDateTime.now(),mockPosts.get(0).getPostId(),mockDevelopers.get(1).getDeveloperId());
		ResponseDto responseDto3 =new ResponseDto(2,"answer2",LocalDateTime.now(),mockPosts.get(1).getPostId(),mockDevelopers.get(1).getDeveloperId());

		when(developerRepository.findById(1)).thenReturn(Optional.of(mockDevelopers.get(0)));
		when(developerRepository.findById(2)).thenReturn(Optional.of(mockDevelopers.get(1)));
		when(postRepository.findById(101)).thenReturn(Optional.of(mockPosts.get(0)));
		when(postRepository.findById(102)).thenReturn(Optional.of(mockPosts.get(1)));
		when(responseRepository.searchByDeveloperId(2)).thenReturn((Arrays.asList(mockResponses.get(1))));
		responseService.addResponse(responseDto1);
		responseService.addResponse(responseDto2);
		responseService.addResponse(responseDto3);
		List <Response> responsesByPost=responseService.getResponseByDeveloper(2);

		assertEquals(responsesByPost.size(),1);
	}
	@Test
	@DisplayName("TESTCASE_FOR_UPDATE_RESPONSE")
	void testUpdateResponse() {
		ResponseDto responseDto =new ResponseDto(1,"answer",LocalDateTime.now(),mockPosts.get(0).getPostId(),mockDevelopers.get(0).getDeveloperId());

		when(developerRepository.findById(1)).thenReturn(Optional.of(mockDevelopers.get(0)));
		when(postRepository.findById(101)).thenReturn(Optional.of(mockPosts.get(0)));
		when(responseRepository.findById(1)).thenReturn(Optional.of(mockResponses.get(0)));
		responseService.addResponse(responseDto);

		responseDto.setAnswer("Updated Answer");
		ResponseDto updatedResponse =responseService.updateResponse(responseDto,1);

		assertEquals(updatedResponse.getAnswer(),"Updated Answer");
	}
	@Test
	@DisplayName("TESTCASE_FOR_ADD_RESPONSE_INVALID_POST")
	void testAddResponseByInvalidPost() {
		int postId=30;
		ResponseDto responseDto =new ResponseDto(1,"answer",LocalDateTime.now(),postId,mockDevelopers.get(0).getDeveloperId());
		assertThrows(ApplicationException.class, () -> responseService.addResponse(responseDto));
	}
	@Test
	@DisplayName("TESTCASE_FOR_GET_RESPONSE_INVALID_POST")
	void testGetResponseByInvalidPost() {
		int responseId=10;
		assertThrows(ApplicationException.class, () -> responseService.getResponseByPost(responseId));
	}
	@Test
	@DisplayName("TESTCASE_FOR_GET_RESPONSE_INVALID_DEVELOPER")
	void testGetResponseByInvalidDeveloper() {
		int developerId=10;
		assertThrows(ApplicationException.class, () -> responseService.getResponseByDeveloper(developerId));
	}

	@Test
	@DisplayName("TEST_FOR_UPDATE_INVALID_RESPONSE")
	void testUpdateInvalidResponse() {
		ResponseDto responseDto =new ResponseDto(1,"answer",LocalDateTime.now(),mockPosts.get(0).getPostId(),mockDevelopers.get(0).getDeveloperId());
		when(developerRepository.findById(1)).thenReturn(Optional.of(mockDevelopers.get(0)));
		when(postRepository.findById(101)).thenReturn(Optional.of(mockPosts.get(0)));
		when(responseRepository.findById(1)).thenReturn(Optional.empty());
		responseService.addResponse(responseDto);
		assertThrows(ApplicationException.class, () -> responseService.updateResponse(responseDto,1));

	}
	@Test
	@DisplayName("TEST_REMOVE_INVALID_RESPONSE")
	void testRemoveInvalidResponse() {
		int responseId=10;
		assertThrows(ApplicationException.class, () -> responseService.removeResponse(responseId));

	}
}

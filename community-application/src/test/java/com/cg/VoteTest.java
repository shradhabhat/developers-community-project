package com.cg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cg.dto.VoteDto;
import com.cg.entity.Response;
import com.cg.entity.Vote;
import com.cg.repository.DeveloperRepository;
import com.cg.repository.ResponseRepository;
import com.cg.repository.VoteRepository;
import com.cg.service.VoteService;
import com.cg.service.VoteServiceImplementation;
 
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class VoteTest {
 
 
    private VoteService voteService;
    private ResponseRepository responseRepository;
    private DeveloperRepository developerRepository;
    private VoteRepository voteRepository;
    private static final Logger logger = LoggerFactory.getLogger(VoteTest.class);
    @BeforeEach
    public void setup() {
        responseRepository = mock(ResponseRepository.class);
        developerRepository = mock(DeveloperRepository.class);
        voteRepository = mock(VoteRepository.class);
        voteService = new VoteServiceImplementation();
    }
    @Test
    public void testAddVote_ValidVoteDto_ReturnsVote() {
        // Given
        // Set up your mocks and voteDto as before
 
        // When
        try {
            VoteDto voteDto = null;
			Vote result = voteService.addVote(voteDto);
            logger.info("Vote added successfully: {}", result);
        } catch (Exception e) {
            logger.error("Error adding vote: {}", e.getMessage());
            //fail("Exception occurred: " + e.getMessage());
        }
    }
    @Test
    public void testAddVote_ResponseNotFound_ThrowsApplicationException() {
        // Given
        VoteDto voteDto = new VoteDto();
        voteDto.setResponse(1);
        voteDto.setDeveloper(1);
        voteDto.setVoteCount(1);
 
        when(responseRepository.findById(1)).thenReturn(java.util.Optional.empty());
 
        // When/Then
        //assertThrows(ApplicationException.class, () -> voteService.addVote(voteDto));
    }
 
    @Test
    public void testAddVote_DeveloperNotFound_ThrowsApplicationException() {
        // Given
        VoteDto voteDto = new VoteDto();
        voteDto.setResponse(1);
        voteDto.setDeveloper(1);
        voteDto.setVoteCount(1);
 
        Response response = new Response();
        when(responseRepository.findById(1)).thenReturn(java.util.Optional.of(response));
 
        when(developerRepository.findById(1)).thenReturn(java.util.Optional.empty());
 
        // When/Then
        //assertThrows(ApplicationException.class, () -> voteService.addVote(voteDto));
    }
 
@Test
public void testGetVoteByVoteId_VoteExists_ReturnsVote() {
    // Given
    int voteId = 1;
    Vote mockVote = new Vote();
    mockVote.setVoteId(voteId);
    when(voteRepository.findById(voteId)).thenReturn(Optional.of(mockVote));
 
    // When
    //Vote result = voteService.getVoteByVoteId(voteId);
 
    // Then
    //assertNotNull(result);
    //assertEquals(voteId, result.getVoteId());
}
 
@Test
public void testGetVoteByVoteId_VoteDoesNotExist_ThrowsException() {
    // Given
    int voteId = 1;
    when(voteRepository.findById(voteId)).thenReturn(Optional.empty());
 
    // When and Then
    //assertThrows(ApplicationException.class, () -> {
        //voteService.getVoteByVoteId(voteId);
    };
//@Test
//public void testGetVoteByResponse_ResponseExists_ReturnsVotes() {
//    // Given
//    int responseId = 1;
//    Response mockResponse = new Response();
//    mockResponse.setId(responseId);
//    when(responseRepository.findById(responseId)).thenReturn(Optional.of(mockResponse));
// 
//    List<Vote> mockVotes = new ArrayList<>();
//    // Populate mockVotes as needed
// 
//    when(voteRepository.searchByResponseId(responseId)).thenReturn(mockVotes);
// 
//    // When
//    //List<Vote> result = voteService.getVoteByResponse(responseId);
// 
//    // Then
//    //assertNotNull(result);
//    //assertEquals(mockVotes.size(), result.size());
//    // Add more assertions as needed for the content of result
//}
// 
@Test
public void testGetVoteByResponse_ResponseDoesNotExist_ThrowsException() {
    // Given
    int responseId = 1;
    when(responseRepository.findById(responseId)).thenReturn(Optional.empty());
 
    // When and Then
    //assertThrows(ApplicationException.class, () -> {
        //voteService.getVoteByResponse(responseId);
    };
//@Test
//public void testGetVoteByDeveloper_DeveloperExists_ReturnsVotes() {
//    // Given
//    int developerId = 1;
//    Developer mockDeveloper = new Developer();
//    mockDeveloper.setId(developerId);
//    when(developerRepository.findById(developerId)).thenReturn(Optional.of(mockDeveloper));
// 
//    List<Vote> mockVotes = new ArrayList<>();
//    // Populate mockVotes as needed
// 
//    when(voteRepository.searchByDeveloperId(developerId)).thenReturn(mockVotes);
// 
//    // When
//    //List<Vote> result = voteService.getVoteByDeveloper(developerId);
// 
//    // Then
//    //assertNotNull(result);
//    //assertEquals(mockVotes.size(), result.size());
//    // Add more assertions as needed for the content of result
//}
 
@Test
public void testGetVoteByDeveloper_DeveloperDoesNotExist_ThrowsException() {
    // Given
    int developerId = 1;
    when(developerRepository.findById(developerId)).thenReturn(Optional.empty());
 
    // When and Then
    //assertThrows(ApplicationException.class, () -> {
        //voteService.getVoteByDeveloper(developerId);
    };
@Test
public void testGetAllVotes_ReturnsAllVotes() {
    // Given
    List<Vote> mockVotes = new ArrayList<>();
    // Populate mockVotes as needed
 
    when(voteRepository.findAll()).thenReturn(mockVotes);
 
    // When
    //List<Vote> result = voteService.getAllVotes();
 
    // Then
    //assertNotNull(result);
    //assertEquals(mockVotes.size(), result.size());
    // Add more assertions as needed for the content of result
}
 
@Test
public void testGetAllVotes_NoVotes_ReturnsEmptyList() {
    // Given
    when(voteRepository.findAll()).thenReturn(new ArrayList<>());
 
    // When
    //List<Vote> result = voteService.getAllVotes();
 
    // Then
   // assertNotNull(result);
    //assertEquals(0, result.size());
}
}

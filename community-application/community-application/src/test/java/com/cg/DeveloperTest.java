package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.entity.Developer;
import com.cg.exception.ApplicationException;
import com.cg.repository.DeveloperRepository;
import com.cg.service.DeveloperServiceImplementation;

//@SpringBootTest
class DeveloperTest {
	//	@MockBean
	@Mock
	private DeveloperRepository developerRepository;

	//	@Autowired
	@InjectMocks
	private DeveloperServiceImplementation developerServiceImpl;

	static List<Developer> mockDevelopers;
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("TESTCASE_FOR_ALLDEVELOPERS")
	void testAllDev() {
		Developer developer1 = new Developer();
		developer1.setUserName("bdroy");
		developer1.setEmailAddress("roy.bd@gmail.com");
		developer1.setDeveloperId(100);
		developer1.setGithubLink("www.github/bd.com");
		developer1.setMemberSince(LocalDate.now());
		developer1.setReputationPoints(45);

		Developer developer2 = new Developer();
		developer2.setUserName("akshi");
		developer2.setEmailAddress("iyer.ak@gmail.com");
		developer2.setDeveloperId(101);
		developer2.setGithubLink("www.github/ak.com");
		developer2.setMemberSince(LocalDate.now());
		developer2.setReputationPoints(40);
		mockDevelopers=Arrays.asList(developer1,developer2);
		when(developerRepository.findAll()).
		thenReturn(mockDevelopers);
		List<Developer> developers = developerServiceImpl.getAllDevelopersService();
		assertEquals(developers.size(), mockDevelopers.size());

	}

	@Test
	@DisplayName("TESTCASE_FOR_ADDDEVELOPER")
	void testAddDev() {
		Developer developer = new Developer();
		developer.setUserName("bdroy");
		developer.setEmailAddress("roy.bd@gmail.com");
		developer.setDeveloperId(100);
		developer.setGithubLink("www.github/bd.com");
		developer.setMemberSince(LocalDate.now());
		developer.setReputationPoints(45);

		when(developerRepository.save(developer)).thenReturn(developer);

		Developer savedDeveloper = developerServiceImpl.addDeveloperService(developer);

		assertNotNull(savedDeveloper);
		assertEquals("bdroy", savedDeveloper.getUserName());
	}

	@Test
	@DisplayName("TESTCASE_FOR_UPDATE")
	void testUpdateDev() {
		int developerId = 100;
		Developer developer1 = new Developer(developerId, "bdroy","roy.bd@gmail.com","www.github/bd.com",45,LocalDate.now());


		developer1.setUserName("akshi");
		developer1.setEmailAddress("iyer.ak@gmail.com");
		developer1.setDeveloperId(developerId);
		developer1.setGithubLink("www.github/bd.com");
		developer1.setMemberSince(LocalDate.now());
		developer1.setReputationPoints(45);		

		assertNotNull(developer1);
		assertEquals("akshi", developer1.getUserName());
		assertEquals("iyer.ak@gmail.com", developer1.getEmailAddress());
		assertEquals("www.github/bd.com", developer1.getGithubLink());
		assertEquals(45, developer1.getReputationPoints());


	}

	@Test
	@DisplayName("TEST_FOR_INVALID_UPDATE")
	void testInvalidUpdate() {
		int developerId = 999; 
		Developer developer = new Developer();
		developer.setDeveloperId(developerId);

		when(developerRepository.findById(developerId)).thenReturn(Optional.empty());


		assertThrows(ApplicationException.class, () -> developerServiceImpl.updateDeveloperService(developer, developerId));
	}

	@Test
	@DisplayName("TESTCASE_FOR_DEVBYREPUTATION")
	void testDevByReputation() {
		int reputationPoint = 90;
		Developer developer1 = new Developer();
		developer1.setUserName("bdroy");
		developer1.setEmailAddress("roy.bd@gmail.com");
		developer1.setDeveloperId(100);
		developer1.setGithubLink("www.github/bd.com");
		developer1.setMemberSince(LocalDate.now());
		developer1.setReputationPoints(reputationPoint);

		developerRepository.developerByReputationRepository(reputationPoint);
		when(developerRepository.findById(100)).thenReturn(Optional.of(developer1));

		Developer developer2 = new Developer();
		developer2.setUserName("akshi");
		developer2.setEmailAddress("iyer.ak@gmail.com");
		developer2.setDeveloperId(101);
		developer2.setGithubLink("www.github/ak.com");
		developer2.setMemberSince(LocalDate.now());
		developer2.setReputationPoints(900);

		if(developer1.getReputationPoints()==reputationPoint)
			mockDevelopers = Arrays.asList(developer1);
		if(developer2.getReputationPoints()==reputationPoint)
			mockDevelopers = Arrays.asList(developer2);

		when(developerServiceImpl.getDeveloperByReputationService(reputationPoint)).thenReturn(mockDevelopers);

		assertEquals(1, mockDevelopers.size());


	}

	@Test
	@DisplayName("TESTCASE_FOR_DEVBYMAXREP")
	void testDevByMaxReputation() {
		int reputationPoint = 90;
		Developer developer1 = new Developer();
		developer1.setUserName("bdroy");
		developer1.setEmailAddress("roy.bd@gmail.com");
		developer1.setDeveloperId(100);
		developer1.setGithubLink("www.github/bd.com");
		developer1.setMemberSince(LocalDate.now());
		developer1.setReputationPoints(reputationPoint);


		Developer developer2 = new Developer();
		developer2.setUserName("akshi");
		developer2.setEmailAddress("iyer.ak@gmail.com");
		developer2.setDeveloperId(101);
		developer2.setGithubLink("www.github/ak.com");
		developer2.setMemberSince(LocalDate.now());
		developer2.setReputationPoints(900);


		mockDevelopers = Arrays.asList(developer1,developer2);
		List<Developer> topDev = new ArrayList<>();
		int max = 0;
		for(Developer d: mockDevelopers){
			if(d.getReputationPoints()>max)
				topDev = Arrays.asList(d);
		}

		when(developerRepository.getByMaxReputationRepository()).thenReturn(topDev);

		assertEquals(Arrays.asList(developer2), developerRepository.getByMaxReputationRepository());
	}

	@Test
	@DisplayName("TESTCASE_FOR_GETBYID")
	void testGetById() {
		int developerId = 1;
		Developer developer = new Developer();
		developer.setDeveloperId(developerId);
		when(developerRepository.findById(developerId)).thenReturn(Optional.of(developer));


		Developer result = developerServiceImpl.getDeveloperByIdService(developerId);


		assertNotNull(result);
		assertEquals(developerId, result.getDeveloperId());
	}

	@Test
	@DisplayName("TESTCASE_FOR_GETBYINVALIDID")
	void testSearchDeveloperByIdNotFound() {
		int developerId = 999; // Invalid developer id
		when(developerRepository.findById(developerId)).thenReturn(Optional.empty());


		assertThrows(ApplicationException.class, () -> developerServiceImpl.getDeveloperByIdService(developerId));
	}

	@Test
	@DisplayName("TESTCASE_FOR_DELETEDEV")
	void testDeleteDevelopers() {
		int developerId = 100;
		Developer developer = new Developer();
		developer.setUserName("bdroy");
		developer.setEmailAddress("roy.bd@gmail.com");
		developer.setDeveloperId(developerId);
		developer.setGithubLink("www.github/bd.com");
		developer.setMemberSince(LocalDate.now());
		developer.setReputationPoints(45);
		developerRepository.save(developer);
		developerRepository.deleteById(developerId);
		assertFalse(developerRepository.findAll().contains(developer));
	}

}
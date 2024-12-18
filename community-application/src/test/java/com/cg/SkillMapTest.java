package com.cg;
import static org.mockito.ArgumentMatchers.argThat;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
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
import com.cg.dto.SkillMapDto;
import com.cg.entity.Developer;
import com.cg.entity.Skill;
import com.cg.entity.SkillDetailId;
import com.cg.entity.SkillMap;
import com.cg.exception.ApplicationException;
import com.cg.repository.DeveloperRepository;
import com.cg.repository.SkillMapRepository;
import com.cg.repository.SkillRepository;
import com.cg.service.SkillMapServiceImplementation;
 
 
public class SkillMapTest {
	@Mock
	private SkillMapRepository skillMapRepository;
	@Mock
	private SkillMapDto skillMapDto;
	@Mock
	private SkillRepository skillRepository;
	@Mock
	private DeveloperRepository developerRepository;
	@InjectMocks
	private SkillMapServiceImplementation skillMapServiceImplementation;
	static SkillDetailId skillDetailId1 = new SkillDetailId();
	static SkillDetailId skillDetailId2 = new SkillDetailId();
	static Developer developer1;
	static Developer developer2;
	static Skill skill1;
	static Skill skill2;
	@BeforeAll
	public static void initMock() {
		 developer1 = new Developer();
		 developer1.setDeveloperId(1001);
		 Skill skill1 = new Skill();
		 skill1.setSkillId((long) 101);
		 skillDetailId1.setDeveloper(developer1);
		 skillDetailId1.setSkill(skill1);
		 developer2 = new Developer();
		 developer2.setDeveloperId(1002);
		 Skill skill2 = new Skill();
		 skill2.setSkillId((long) 102);
		 skillDetailId2.setDeveloper(developer2);
		 skillDetailId2.setSkill(skill2);
	}
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	@DisplayName("TESTCASE_FOR_ADDSKILLMAP")
	 public void testAddSkillMap() {
        // Mock data
        SkillMapDto skillMapDto = new SkillMapDto( "beginner", "3 years", "Developer Community System", 1002, 101L);
        Developer developer = new Developer();
        developer.setDeveloperId(1002);
        Skill skill = new Skill();
        skill.setSkillId(101L);
 
        // Mock behavior of repositories
        when(developerRepository.findById(1002)).thenReturn(Optional.of(developer));
        when(skillRepository.findById(101L)).thenReturn(Optional.of(skill));
        // Test addSkillMap method
        SkillMap savedSkillMap = skillMapServiceImplementation.addSkillMap(skillMapDto);
        // Verify repository method calls
        verify(developerRepository, times(1)).findById(1002);
        verify(skillRepository, times(1)).findById(101L);
        verify(skillMapRepository, times(1)).save(any(SkillMap.class));
        System.out.println(savedSkillMap.getExperienceLevel());
        // Assert that savedSkillMap is not null and contains expected values
        assertNotNull(savedSkillMap);
        assertEquals("beginner", savedSkillMap.getProfeciencyLevel());
        assertEquals( "3 years",savedSkillMap.getExperienceLevel());
        assertEquals("Developer Community System",savedSkillMap.getProjects());
    }
	@Test
	@DisplayName("TESTCASE_FOR_ALLSKILLMAP")
	public void gSkillMap() {
	    // Setup test data

	    SkillMap skillMap1 = new SkillMap("beginner", "3 years", "Developer Community System", skillDetailId1);
	    SkillMap skillMap2 = new SkillMap("intermediate", "4 years", "Food Delivery System", skillDetailId2);
	    List<SkillMap> skillMapList = Arrays.asList(skillMap1, skillMap2);
	    skillMapRepository.save(skillMap1);
	    skillMapRepository.save(skillMap2);
 
	    when(skillMapRepository.findAll()).thenReturn(skillMapList);
	    List<SkillMap> skillMapListReturned = skillMapServiceImplementation.getAllSkillMaps();
 
	    assertEquals(skillMapListReturned.size(), skillMapList.size());
	    assertEquals("intermediate",skillMapList.get(1).getProfeciencyLevel());
	}	
	@Test
	@DisplayName("TESTCASE_FOR_FINDBYDEVELOPERID_SUCCESS")
	public void testFindByDeveloperId() {
		SkillMap skillMap1 = new SkillMap("beginner", "3 years", "Developer Community System", skillDetailId1);   
	    SkillMap skillMap2 = new SkillMap("intermediate", "4 years", "Food Delivery System", skillDetailId2);
 
		Skill skill3 = new Skill();
		skill3.setSkillId((long) 103);
		SkillDetailId skillDetailId3 = new SkillDetailId(skill3,developer1);
	    SkillMap skillMap3 = new SkillMap("intermediate", "4 years", "Food Delivery System", skillDetailId3);
		List<SkillMap> skillMapList = Arrays.asList(skillMap1, skillMap3);
	    skillMapRepository.save(skillMap1);
	    skillMapRepository.save(skillMap2);
	    skillMapRepository.save(skillMap3);
		when(developerRepository.findById(1001)).thenReturn(Optional.of(developer1));
		when(skillMapRepository.findBySkillDetailIdDeveloper(developer1)).thenReturn(skillMapList);
 
		List<SkillMap> result = skillMapServiceImplementation.findByDeveloperId(1001);
		assertEquals("Developer Community System",result.get(0).getProjects());
		assertEquals(2, result.size());	
	}
	@Test
	@DisplayName("TESTCASE_FOR_FINDBYDSKILLID_SUCCESS")
	public void testFindBySkillId() {
		Developer developer1 = new Developer();
		developer1.setDeveloperId(1001);
		Skill skill1 = new Skill();
		skill1.setSkillId((long) 101);
		skillDetailId1.setDeveloper(developer1);
		skillDetailId1.setSkill(skill1);
		developer2 = new Developer();
		developer2.setDeveloperId(1002);
		Skill skill2 = new Skill();
		skill2.setSkillId((long) 102);
		skillDetailId2.setDeveloper(developer2);
		skillDetailId2.setSkill(skill2);
		SkillMap skillMap1 = new SkillMap("beginner", "3 years", "Developer Community System", skillDetailId1);   
	    SkillMap skillMap2 = new SkillMap("intermediate", "4 years", "Food Delivery System", skillDetailId2);
	    Developer developer3 = new Developer();
		developer3.setDeveloperId(1003);
		SkillDetailId skillDetailId3 = new SkillDetailId(skill1,developer3);
		SkillMap skillMap3 = new SkillMap("intermediate", "4 years", "Food Delivery System", skillDetailId3);
		skillMapRepository.save(skillMap1);
	    skillMapRepository.save(skillMap2);
	    skillMapRepository.save(skillMap3);
	    List<SkillMap> skillMapList = Arrays.asList(skillMap1, skillMap3);
	    when(skillRepository.findById(101L)).thenReturn(Optional.of(skill1));
	    when(skillMapRepository.findBySkillDetailIdSkill(skill1)).thenReturn(skillMapList);
 
	    List<SkillMap> result = skillMapServiceImplementation.findBySkillId(skill1.getSkillId());
	    assertEquals("Developer Community System",result.get(0).getProjects());
		assertEquals(2, result.size());	
	}

 
 
	
	@Test
	@DisplayName("TESTCASE_FOR_FINDBYID")
	public void testGetsSkillMap() {
	    Developer developer = new Developer();
	    developer.setDeveloperId(1001);
	    Skill skill = new Skill();
	    skill.setSkillId((long) 101);
	    when(developerRepository.findById(1001)).thenReturn(Optional.of(developer));
	    when(skillRepository.findById(101L)).thenReturn(Optional.of(skill));
	    SkillDetailId skillDetailId = new SkillDetailId();
	    skillDetailId.setDeveloper(developer);
	    skillDetailId.setSkill(skill);
	    SkillMap skillMap = new SkillMap("beginner", "3 years", "Developer Community System", skillDetailId);   
	    when(skillMapRepository.findById(argThat(new SkillDetailIdMatcher(skillDetailId)))).thenReturn(Optional.of(skillMap));
 
	    SkillMap returnedSkillMap = skillMapServiceImplementation.getSkillMapById((long)101, 1001);
	    System.out.println(returnedSkillMap);
	    System.out.println(returnedSkillMap.getProfeciencyLevel());
	    assertEquals("Developer Community System",returnedSkillMap.getProjects());
	}

	@Test
	@DisplayName("TESTCASE_FOR_UPDATESKILLMAP")
	public void testUpdateSkillMap_Success() {
		SkillMapDto skillMapDto = new SkillMapDto("beginner", "top-level", "Developer-Community", 1002, 101L);
		Developer developer = new Developer();
		developer.setDeveloperId(1002);
		Skill skill = new Skill();
		skill.setSkillId(101L);
		SkillDetailId skillDetailId = new SkillDetailId(skill,developer); 
		SkillMap skillMap = new SkillMap("intermediate", "senior", "Developer-Community", skillDetailId);
		when(developerRepository.findById(1002)).thenReturn(Optional.of(developer));
		when(skillRepository.findById(101L)).thenReturn(Optional.of(skill));
		when(skillMapRepository.findById(argThat(new SkillDetailIdMatcher(skillDetailId)))).thenReturn(Optional.of(skillMap));
		when(skillMapRepository.save(any(SkillMap.class))).thenReturn(skillMap);
		SkillMap updatedSkillMap = skillMapServiceImplementation.updateSkillMap(101L, 1002, skillMapDto);
		assertNotNull(skillRepository);
		assertNotNull(updatedSkillMap);
		assertEquals("beginner",updatedSkillMap.getProfeciencyLevel());
	}

 
	
	@Test
	@DisplayName("TESTCASE_FOR_FINDBYDSKILLID_FAIL")
	void testFindBySkillIdFail() {
		Long skillId=(long)230;
		assertThrows(ApplicationException.class, () -> {
			skillMapServiceImplementation.findBySkillId(skillId);
		},"Skill not found");
	}
	@Test
	@DisplayName("TESTCASE_FOR_FINDBYDEVELOPERID_FAIL")
	void testFindByDeveloperIdFail() {
		int developerId= 1006;
		assertThrows(ApplicationException.class, () -> {
			skillMapServiceImplementation.findByDeveloperId(developerId);
		},"Developer not found");
	}
 
 
	
	@Test
	@DisplayName("TESTCASE_FOR_UPDATESKILLMAP_FAIL")
	public void testUpdateSkillMap_NotFound() {
		Developer developer = new Developer();
		developer.setDeveloperId(2000);
		Skill skill= new Skill();
		skill.setSkillId(200L);

 
		assertThrows(RuntimeException.class, () -> {
			skillMapServiceImplementation.updateSkillMap(skill.getSkillId(), developer.getDeveloperId(), skillMapDto);
		}, "SkillMap not found");
	}

	@Test
	@DisplayName("TESTCASE_FOR_DELETESKILLMAP_SUCCESS")
	public void testDeleteSkillMap_Success() {
		Developer developer1 = new Developer();
		developer1.setDeveloperId(1001);
		Skill skill1 = new Skill();
		skill1.setSkillId((long) 101);
		skillDetailId1.setDeveloper(developer1);
		skillDetailId1.setSkill(skill1);
		SkillMap skillMap1 = new SkillMap("beginner", "3 years", "Developer Community System", skillDetailId1); 
		skillMapRepository.save(skillMap1);
		when(developerRepository.findById(1001)).thenReturn(Optional.of(developer1));
		when(skillRepository.findById(101L)).thenReturn(Optional.of(skill1));
		when(skillMapRepository.findById(skillDetailId1)).thenReturn(Optional.of(skillMap1));
		skillMapRepository.deleteById(skillDetailId1);
		assertFalse(skillMapRepository.findAll().contains(skillMap1));
	}
	@Test
	@DisplayName("TESTCASE_FOR_DELETESKILLMAP_FAIL")
	public void testDeleteSkillMap_NotFound() {
		Developer developer = new Developer();
		developer.setDeveloperId(2000);
		Skill skill= new Skill();
		skill.setSkillId(200L);
 
		assertThrows(RuntimeException.class, () -> {
			skillMapServiceImplementation.deleteSkillMap(skill.getSkillId(), developer.getDeveloperId());
		}, "SkillMap not found");
	}
}
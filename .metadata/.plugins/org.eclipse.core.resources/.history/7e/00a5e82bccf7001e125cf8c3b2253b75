package com.cg;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
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
import com.cg.repository.DeveloperRepository;
import com.cg.repository.SkillMapRepository;
import com.cg.repository.SkillRepository;
import com.cg.service.SkillMapServiceImplementation;
//@SpringBootTest
//@Configuration
public class TestSkillMapTest {
	@Mock
//	@MockBean
	private SkillMapRepository skillMapRepository;
	@Mock
	//@MockBean
	private SkillMapDto skillMapDto;
	@Mock
	//@MockBean
	private SkillRepository skillRepository;
	@Mock
	//@MockBean
	private DeveloperRepository developerRepository;
	@InjectMocks
	//@Autowired
	private SkillMapServiceImplementation skillMapServiceImplementation;
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	@DisplayName("TESTCASE_FOR_ADDSKILLMAP")
	 public void testAddSkillMap() {
        // Mock data
        SkillMapDto skillMapDto = new SkillMapDto("good", "bad", "three", "four", 1002, 101L);
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
        assertEquals("good", savedSkillMap.getSkillDescription());
        // Add more assertions as needed for other properties
    }
//	
	@Test
    @DisplayName("TESTCASE_FOR_ALLSKILLMAP")
    public void testAllSkillMap() {

 
    	SkillMapDto skillMapDto1= new SkillMapDto("good", "bad", "three", "four", 1001, 101L);
    	Developer developer1 = new Developer();
    	developer1.setDeveloperId(1001);
    	Skill skill1= new Skill();
    	skill1.setSkillId((long)101);
    	SkillMapDto skillMapDto2= new SkillMapDto("what", "is", "yoiur", "name", 1002, 102L);
    	SkillDetailId skillDetailId1= new SkillDetailId(skill1,developer1);
    	Developer developer2 = new Developer();
    	developer2.setDeveloperId(1002);
    	Skill skill2= new Skill();
    	skill2.setSkillId((long)102);
    	SkillDetailId skillDetailId2= new SkillDetailId(skill1,developer2);
    	SkillMap skillMap1= new SkillMap("good", "bad", "three", "four", skillDetailId1);
    	SkillMap skillMap2= new SkillMap("what", "is", "yoiur", "name", skillDetailId2);
    	List<SkillMap> skillMapList=Arrays.asList(skillMap1,skillMap2);
//    	SkillMap skillMap= new SkillMap("what", "is", "yoiur", "name", skillDetailId2);
    	skillMapRepository.save(skillMap1);
    	skillMapRepository.save(skillMap2);
    	when(developerRepository.findById(1002)).thenReturn(Optional.of(developer2));
    	when(skillRepository.findById((long)102)).thenReturn(Optional.of(skill2));
    	when(skillMapRepository.findById(skillDetailId2)).thenReturn(Optional.of(skillMapList.get(1)));
    	when(developerRepository.findById(1001)).thenReturn(Optional.of(developer1));
    	when(skillRepository.findById((long)101)).thenReturn(Optional.of(skill1));
    	when(skillMapRepository.findById(skillDetailId1)).thenReturn(Optional.of(skillMapList.get(0)));
    	when(skillMapRepository.findAll()).thenReturn(skillMapList);
    	List <SkillMap> skillMapListss=skillMapServiceImplementation.getAllSkillMaps();
    	System.out.println(skillMapListss.size());
    	assertEquals(skillMapListss.size(),2);
//    	skillMapDto2.setExperienceLevel("shashasha");
////    	
//    	SkillMap updatedSkillMap = skillMapServiceImplementation.updateSkillMap(102L, 1002, skillMapDto2);
//    	assertEquals("shashasha",updatedSkillMap.getExperienceLevel());
    }
	@Test
	@DisplayName("TESTCASE_FOR_ALLSKILLMAP")
	public void gSkillMap() {
	    // Setup test data
	    Developer developer1 = new Developer();
	    developer1.setDeveloperId(1001);
	    Skill skill1 = new Skill();
	    skill1.setSkillId((long) 101);
	    SkillDetailId skillDetailId1 = new SkillDetailId(skill1, developer1);
	    SkillMap skillMap1 = new SkillMap("good", "bad", "three", "four", skillDetailId1);
 
	    Developer developer2 = new Developer();
	    developer2.setDeveloperId(1002);
	    Skill skill2 = new Skill();
	    skill2.setSkillId((long) 102);
	    SkillDetailId skillDetailId2 = new SkillDetailId(skill2, developer2);
	    SkillMap skillMap2 = new SkillMap("what", "is", "your", "name", skillDetailId2);
 
	    List<SkillMap> skillMapList = Arrays.asList(skillMap1, skillMap2);
	    skillMapRepository.save(skillMap1);
	    skillMapRepository.save(skillMap2);
	    // Mock repository responses
//	    when(developerRepository.findById(1001)).thenReturn(Optional.of(developer1));
//	    when(skillRepository.findById((long) 101)).thenReturn(Optional.of(skill1));
//	    when(skillMapRepository.findById(skillDetailId1)).thenReturn(Optional.of(skillMap1));
//
//	    when(developerRepository.findById(1002)).thenReturn(Optional.of(developer2));
//	    when(skillRepository.findById((long) 102)).thenReturn(Optional.of(skill2));
//	    when(skillMapRepository.findById(skillDetailId2)).thenReturn(Optional.of(skillMap2));
	    when(skillMapRepository.findAll()).thenReturn(skillMapList);
	    // Invoke service method
	    List<SkillMap> skillMapListReturned = skillMapServiceImplementation.getAllSkillMaps();
 
	    // Assertion
	    assertEquals(skillMapListReturned.size(), skillMapList.size());
	}	
}
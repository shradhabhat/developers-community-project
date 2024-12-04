package com.cg;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.dto.SkillMapDto;
import com.cg.entity.Developer;
import com.cg.entity.Skill;
import com.cg.entity.SkillDetailId;
import com.cg.entity.SkillMap;
import com.cg.repository.DeveloperRepository;
import com.cg.repository.SkillMapRepository;
import com.cg.repository.SkillRepository;
import com.cg.service.SkillMapService;
import static org.mockito.ArgumentMatchers.*;
import com.cg.service.SkillMapServiceImplementation;

@ExtendWith(MockitoExtension.class)
public class SkillMapTest {

	@Mock
	private SkillMapRepository skillMapRepository;

	@Mock
	private SkillRepository skillRepository;

	@Mock
	private DeveloperRepository developerRepository;

	@InjectMocks
	private SkillMapServiceImplementation skillMapService;

	private SkillMapDto skillMapDto;
	private SkillMap skillMap;
	private Developer developer;
	private Skill skill;

	@BeforeEach
	public void setup() {
		skillMapDto = new SkillMapDto();
		skillMapDto.setDeveloperId(1);
		skillMapDto.setSkillId(1L);
		skillMapDto.setSkillDescription("Java");
		skillMapDto.setProfeciencyLevel("Beginner");
		skillMapDto.setExperienceLevel("4 years");
		skillMapDto.setProjects("Done");
		skillMap = new SkillMap();
		skillMap.setSkillDescription("Java");
		skillMap.setProfeciencyLevel("Beginner");
		skillMap.setExperienceLevel("4 years");
		skillMap.setProjects("Done");
		developer = new Developer();
		developer.setDeveloperId(1);

		skill = new Skill();
		skill.setSkillId(1L);
		skillMap.setSkillDetailId(new SkillDetailId(skill,developer));



		//        skillMap.getSkillDetailId().setDeveloper(developer);
		//        skillMap.getSkillDetailId().setSkill(skill);
	}
	@BeforeEach
	public void set(){
		MockitoAnnotations.openMocks(this); //without this you will get NPE
	}



	@Test
	public void testAddSkillMapss() {
		when(developerRepository.findById(1)).thenReturn(Optional.of(developer));
		when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));

		skillMapService.addSkillMap(skillMapDto);

		verify(skillMapRepository, times(1)).save(any(SkillMap.class));
	}
	@Test
	@DisplayName("TESTCASE_FOR_ADDSKILLMAP")
	public void testAddSkillMaps() {
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
		SkillMap savedSkillMap = skillMapService.addSkillMap(skillMapDto);

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

	@Test
	public void testFindByDeveloperId() {
		List<SkillMap> skillMapList = new ArrayList<>();
		skillMapList.add(skillMap);

		when(developerRepository.findById(1)).thenReturn(Optional.of(developer));
		when(skillMapRepository.findBySkillDetailIdDeveloper(developer)).thenReturn(skillMapList);

		List<SkillMap> result = skillMapService.findByDeveloperId(1);

		assertEquals(skillMapList, result);
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
		SkillMap savedSkillMap = skillMapService.addSkillMap(skillMapDto);

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
		when(skillMapRepository.findAll()).thenReturn(skillMapList);
		List <SkillMap> skillMapListss=skillMapService.getAllSkillMaps();
		System.out.println(skillMapListss.size());
		assertEquals(skillMapListss.size(),2);
	}


	@Test
	public void testUpdateSkillMap() {
		// Mock SkillMapDto
		SkillMapDto updatedSkillMapDto = new SkillMapDto();
		updatedSkillMapDto.setDeveloperId(1);
		updatedSkillMapDto.setSkillId(1L);
		updatedSkillMapDto.setSkillDescription("Updated Java");
		updatedSkillMapDto.setProfeciencyLevel("updated Beginner");
		updatedSkillMapDto.setExperienceLevel("5 years");
		updatedSkillMapDto.setProjects("not Done");
		SkillMapDto skillMapDto = new SkillMapDto();
		skillMapDto.setDeveloperId(1);
		skillMapDto.setSkillId(1L);
		skillMapDto.setSkillDescription("Java");
		skillMapDto.setProfeciencyLevel("Beginner");
		skillMapDto.setExperienceLevel("4 years");
		skillMapDto.setProjects("Done");
		SkillMap skillMap = new SkillMap();
		skillMap.setSkillDescription("Java");
		skillMap.setProfeciencyLevel("Beginner");
		skillMap.setExperienceLevel("4 years");
		skillMap.setProjects("Done");
		developer = new Developer();
		developer.setDeveloperId(1);

		skill = new Skill();
		skill.setSkillId(1L);
		skillMap.setSkillDetailId(new SkillDetailId(skill,developer));

		// Mock findById for Developer and Skill
		when(developerRepository.findById(1)).thenReturn(Optional.of(developer));
		when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));
		// Mock findById for SkillMap
		when(skillMapRepository.findById(any(SkillDetailId.class))).thenReturn(Optional.of(skillMap));
		//
		//        // Mock findById for SkillMap
		// when(skillMapRepository.findById(skillDetailsId)).thenReturn(Optional.of(existingSkillMap));

		// Test
		SkillMap updatedSkillMap = skillMapService.updateSkillMap(1L,1,updatedSkillMapDto);
		assertEquals("Updated Java",updatedSkillMap.getSkillDescription());
	}


	@Test
	public void testDeleteSkillMap_Success() {
		// Mock Developer
		Developer developer = new Developer();
		developer.setDeveloperId(1);

		// Mock findById for Developer
		when(developerRepository.findById(1)).thenReturn(Optional.of(developer));

		// Mock findById for Skill
		when(skillRepository.findById(anyLong())).thenReturn(Optional.of(new Skill()));
		when(skillMapRepository.findById(any(SkillDetailId.class))).thenReturn(Optional.of(skillMap));

		// Test
		assertDoesNotThrow(() -> skillMapService.deleteSkillMap(1L, 1));
	}
	@Test
	public void testFindBySkillId() {
		// Mock Skill
		Skill skill = new Skill();
		skill.setSkillId(1L);

		// Mock SkillMap
		SkillMap skillMap1 = new SkillMap();
		SkillMap skillMap2 = new SkillMap();
		SkillMap skillMap3= new SkillMap();
		List<SkillMap> skillMaps = Arrays.asList(skillMap1, skillMap2,skillMap3);

		// Mock findById for Skill
		when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));

		// Mock findBySkillDetailIdSkill for SkillMapRepository
		when(skillMapRepository.findBySkillDetailIdSkill(skill)).thenReturn(skillMaps);

		// Test
		List<SkillMap> result = skillMapService.findBySkillId(1L);
		assertEquals(3, result.size());
	}



	@Test
	public void testDeleteSkillMap_NotFound() {
		// Arrange
		long skillId = 1L;
		int developerId = 1;
		SkillDetailId skillDetailId = skillMap.getSkillDetailId();


		// Act & Assert
		assertThrows(RuntimeException.class, () -> {
			skillMapService.deleteSkillMap(skillId, developerId);
		}, "SkillMap not found");
	}

	@Test
	public void testUpdateSkillMap_Success() {
		SkillMapDto skillMapDto = new SkillMapDto("good", "bad", "three", "four", 1002, 101L);
		Developer developer = new Developer();
		developer.setDeveloperId(1002);
		Skill skill = new Skill();
		skill.setSkillId(101L);
		SkillDetailId skillDetailId = new SkillDetailId(skill,developer); 
		SkillMap skillMap = new SkillMap("good", "bad", "three", "four", skillDetailId);
		// Mock behavior of repositories
		when(developerRepository.findById(1002)).thenReturn(Optional.of(developer));
		when(skillRepository.findById(101L)).thenReturn(Optional.of(skill));
		when(skillMapRepository.findById(skillDetailId)).thenReturn(Optional.of(skillMap));
		// Test addSkillMap method
		SkillMap savedSkillMap = skillMapService.addSkillMap(skillMapDto);
		SkillMap updatedSkillMap = skillMapService.updateSkillMap(101L, 1002, skillMapDto);
		assertNotNull(skillRepository);
		assertNotNull(updatedSkillMap);
	}

	@Test
	public void testUpdateSkillMap_NotFound() {
		// Arrange
		long skillId = 1L;
		int developerId = 1;
		SkillDetailId skillDetailId = skillMap.getSkillDetailId();


		// Act & Assert
		assertThrows(RuntimeException.class, () -> {
			skillMapService.updateSkillMap(skillId, developerId, skillMapDto);
		}, "SkillMap not found");
	}
}
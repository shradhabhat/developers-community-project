package com.cg;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
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

import com.cg.entity.Skill;
import com.cg.exception.ApplicationException;
import com.cg.repository.SkillRepository;
import com.cg.service.SkillServiceImplementation;
public class SkillTest {
	@Mock
	private SkillRepository skillRepository;
	@InjectMocks
	private SkillServiceImplementation skillServiceImplementation;
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	List<Skill>  mockSkill = new ArrayList<>();
	@Test
	@DisplayName("TESTCASE_FOR_ADDSKILL")
	public void testAddSkill() {
		Skill skill = new Skill((long) 101,"Java","a programming lang","None");
		when(skillRepository.save(skill)).thenReturn(skill);
		Skill savedSkill = skillServiceImplementation.addSkill(skill);
		assertNotNull(savedSkill);
		assertEquals("java",savedSkill.getSkillName());
	}

 
	@Test
	@DisplayName("TESTCASE_FOR_ALLSKILLS")
	public void testAllSkills() {
		Skill skill1 = new Skill((long) 101,"Java","a programming lang","None");
		Skill skill2 = new Skill((long) 102,"JavaScript","a programming lang","css, html");
		mockSkill = Arrays.asList(skill1,skill2);
		when(skillRepository.findAll()).thenReturn(mockSkill);
		List<Skill> skills = skillServiceImplementation.getAllSkills();
		assertEquals(skills.size(),mockSkill.size());
	}
	@Test
	@DisplayName("TESTCASE_FOR_UPDATE")
	void testUpdateSkills() {
		Skill skill = new Skill((long) 101,"Java","a programming lang","None");
		skillRepository.save(skill);
		Long skillId=(long)101;
		when(skillRepository.findById(skillId)).thenReturn(Optional.of(skill));
		skill.setSkillDescription("an oop language");
		skill.setDependencySkill("OOPS");
		Skill updateSkill = skillServiceImplementation.updateSkill((long)101, skill);
		assertNotNull(skill);
		assertEquals("an oop language",updateSkill.getSkillDescription());
		assertEquals("OOPS",updateSkill.getDependencySkill());
	}
	@Test
	@DisplayName("TEST_FOR_INVALID_UPDATE")
	void testInvalidUpdate() {
		Skill skill = new Skill((long) 101,"Java","a programming lang","None");
		skillRepository.save(skill);
		Long skillId = (long)123; 
		Skill skill2 = new Skill();
		skill2.setSkillId(skillId);
		when(skillRepository.findById(skillId)).thenReturn(Optional.empty());
		assertThrows(ApplicationException.class, () -> skillServiceImplementation.updateSkill(skillId, skill2));
	}

	@Test
	@DisplayName("TESTCASE_FOR_DELETESKILL")
	void testDeleteSkills() {
		Skill skill = new Skill((long)101,"Java","a programming lang","None");
		skillRepository.save(skill);
		Long skillId=(long)101;
		when(skillRepository.findById(skillId)).thenReturn(Optional.of(skill));
		skillRepository.deleteById(skillId);
		assertFalse(skillRepository.findAll().contains(skill));
	}
	@Test
	@DisplayName("TESTCASE_FOR_GETBYID")
	public void testGetSkillById() {
		Skill skill= new Skill((long)101,"Java","a programming lang","css,html");
		when(skillRepository.findById(skill.getSkillId())).thenReturn(Optional.of(skill));
		Skill result = skillServiceImplementation.getSkillById(skill.getSkillId());
		assertNotNull(skill);
		assertEquals(skill.getSkillId(),result.getSkillId());
	}
	@Test
	@DisplayName("TESTCASE_FOR_GET_INVALID_GETBYID")
	void testInvalidGetSkillById() {
		int skillId=10;
		assertThrows(ApplicationException.class, () -> skillServiceImplementation.getSkillById((long)skillId));
	}
 
}
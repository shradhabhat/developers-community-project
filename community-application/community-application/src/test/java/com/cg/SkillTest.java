package com.cg;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
		Skill skill = new Skill((long) 101,"Java","css, html");
		when(skillRepository.save(skill)).thenReturn(skill);
		Skill savedSkill = skillServiceImplementation.addSkill(skill);
		assertNotNull(savedSkill);
		assertEquals("java",savedSkill.getSkillName());
	}

	@Test
	@DisplayName("TESTCASE_FOR_ALLSKILLS")
	public void testAllSkills() {
		Skill skill1 = new Skill((long) 101,"Java","css, html");
		Skill skill2 = new Skill((long) 102,"JavaScript","css, html");
		mockSkill = Arrays.asList(skill1,skill2);
		when(skillRepository.findAll()).thenReturn(mockSkill);
		List<Skill> skills = skillServiceImplementation.getAllSkills();
		assertEquals(skills.size(),mockSkill.size());
	}
	@Test
	@DisplayName("TESTCASE_FOR_UPDATE")
	void testUpdateSkills() {
		Skill skill = new Skill((long) 101,"Java","css, html");
		skill.setSkillName("Java8");
		skill.setDependencySkill("OOPS");
		assertNotNull(skill);
		assertEquals("Java8",skill.getSkillName());
		assertEquals("OOPS",skill.getDependencySkill());
	}
	@Test
	@DisplayName("TESTCASE_FOR_DELETESKILL")
	void testDeleteSkills() {
		Skill skill = new Skill((long)101,"Java","css, html");
		skillRepository.save(skill);
		skillRepository.deleteById(skill.getSkillId());
		assertFalse(skillRepository.findAll().contains(skill));
	}
	@Test
	@DisplayName("TESTCASE_FOR_GETBYID")
	public void testGetSkillById() {
		Skill skill= new Skill((long)101,"Java","css,html");
		when(skillRepository.findById(skill.getSkillId())).thenReturn(Optional.of(skill));
		Skill result = skillServiceImplementation.getSkillById(skill.getSkillId());
		assertNotNull(skill);
		assertEquals(skill.getSkillId(),result.getSkillId());
	}

}
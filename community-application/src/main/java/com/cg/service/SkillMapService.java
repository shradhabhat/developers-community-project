package com.cg.service;
import java.util.List;

import com.cg.dto.SkillMapDto;
import com.cg.entity.Skill;
import com.cg.entity.SkillMap;

public interface SkillMapService {
	public SkillMap addSkillMap(SkillMapDto skilldto);
	public List<SkillMap> getAllSkillMaps();
	public SkillMap getSkillMapById(Long skillId,int developerId);
	public SkillMap updateSkillMap(Long skillId,int developerId,SkillMapDto updatedSkill);
	public void deleteSkillMap(Long skillId,int developerId);
	public List<SkillMap> findByDeveloperId(int developerId);
	public List<SkillMap> findBySkillId(Long skillId);
	public List<Skill> findSkillByDeveloperId(int developerId);
}
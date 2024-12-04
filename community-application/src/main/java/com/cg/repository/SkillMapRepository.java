package com.cg.repository;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Developer;
import com.cg.entity.Skill;
import com.cg.entity.SkillDetailId;
import com.cg.entity.SkillMap;

public interface SkillMapRepository extends JpaRepository<SkillMap,SkillDetailId>{
	List<SkillMap> findBySkillDetailIdDeveloper(Developer developer);
	List<SkillMap> findBySkillDetailIdSkill(Skill skill);
}
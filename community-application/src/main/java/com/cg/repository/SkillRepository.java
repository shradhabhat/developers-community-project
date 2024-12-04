package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {

}

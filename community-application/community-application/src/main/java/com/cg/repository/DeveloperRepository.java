package com.cg.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.Developer;
@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer>{

	@Query(value="Select d from Developer d where d.reputationPoints=:addr")
	List<Developer> developerByReputationRepository(@Param("addr") int reputation);

	@Query(value="Select DISTINCT d from Developer d where d.reputationPoints=(Select MAX(d.reputationPoints) FROM Developer d)")
	List<Developer> getByMaxReputationRepository();
}

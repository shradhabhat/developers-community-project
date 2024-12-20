package com.cg.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.entity.Developer;
 
public interface UserRepository extends JpaRepository<Developer,Integer>{
 
	@Query(value="select id from user_credential where username = :un",nativeQuery=true)
	int getUserId(@Param("un")   String username);
}
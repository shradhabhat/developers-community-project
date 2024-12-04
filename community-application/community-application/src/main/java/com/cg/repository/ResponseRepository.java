package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.Response;
@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer>{
	@Query(value="select r from Response r where r.post.postId= :i")
	List<Response> searchByPostId(@Param("i") int postId);


	@Query(value="select r from Response r where r.developer.developerId = :i")
	List<Response> searchByDeveloperId(@Param("i") int developerId);


}

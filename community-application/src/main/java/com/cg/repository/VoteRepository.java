package com.cg.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import com.cg.entity.Vote;
 
@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
	@Query(value="select v from Vote v where v.response.responseId= :i")
	List<Vote> searchByResponseId(@Param("i") int responseId);
 
 
	@Query(value="select v from Vote v where v.developer.developerId = :i")
	List<Vote> searchByDeveloperId(@Param("i") int developerId);
 
 
}
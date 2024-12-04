package com.cg.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import com.cg.entity.Post;
 
@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	 	@Query("SELECT p FROM Post p WHERE p.query LIKE %:keyword% OR p.topic LIKE %:keyword%")
	    List<Post> searchPost(@Param("keyword")String keyword);
 
//	    @Query("SELECT p FROM Post p WHERE p.topic = :topic")
//	    List<Post> findByTopicIgnoreCase(@Param("topic")String topic);
	    @Query(value="Select r from Post r where r.developer.developerId = :i")
		List<Post> searchByDeveloperId(@Param("i") int developerId);

}
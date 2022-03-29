package com.javainuse.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction,Integer> {
	List<Reaction> findByPostAndUser(Post post, User user);
	List<Reaction> findByPostIDAndUserID(Integer postID, Integer userID);
	
	boolean existsByPostAndUser(Post post, User user);
	boolean existsByPostIDAndUserID(Integer postID, Integer userID);
}

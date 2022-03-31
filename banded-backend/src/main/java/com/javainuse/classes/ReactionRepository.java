package com.javainuse.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction,Integer> {
	List<Reaction> findByUserID(Integer userID);
	long countByPostID(Integer postID);
	boolean existsByPostAndUser(Post post, User user);
	boolean existsByPostIDAndUserID(Integer postID, Integer userID);
}

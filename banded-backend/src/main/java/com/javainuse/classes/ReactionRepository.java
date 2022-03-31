package com.javainuse.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction,Integer> {
	List<Reaction> findByUserID(Integer userID);
	Optional<Reaction> findByPostAndUser(Post post, User user);
	long countByPostID(Integer postID);
	boolean existsByPostAndUser(Post post, User user);
	boolean existsByUserAndPostID(User user, Integer postID);
	boolean existsByPostIDAndUserID(Integer postID, Integer userID);
}

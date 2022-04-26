package com.javainuse.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicFollowerRepository extends JpaRepository<TopicFollower,Integer> {
	List<TopicFollower> findByUserID(Integer userID); //retrieves all rows for given userID, i.e. List of TopicFollower objects (all the topics user is following)
	List<TopicFollower> findByUser(User user);
	Optional<TopicFollower> findByUserAndTopic(User user, Topic topic);
	boolean existsByUserAndTopic(User user, Topic topic); //checks if a TopicFollower row already exists that contains given User and Topic
	
}

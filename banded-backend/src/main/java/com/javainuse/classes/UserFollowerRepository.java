package com.javainuse.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFollowerRepository extends JpaRepository<UserFollower,Integer> {
	List<UserFollower> findByFollowingID(Integer followingID); //given id of user, returns userfollower objects with ids of the people they follow
	List<UserFollower> findByFollowedID(Integer followedID); //given id of user, returns userfollower objects with ids of people that follow them
	
	Optional<UserFollower> findByFollowingUserAndFollowedUser(User followingUser, User followedUser);
	
	boolean existsByFollowingIDAndFollowedID(Integer followingID, Integer followedID);
	boolean existsByFollowingUserAndFollowedUser(User followingUser, User followedUser);
}

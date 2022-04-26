package com.javainuse.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlockRepository extends JpaRepository<Block,Integer> {
	Optional<Block> findByBlockingUserAndBlockedUser(User blockingUser, User blockedUser);
	List<Block> findByBlockingID(Integer blockingID);
	List<Block> findByBlockedID(Integer blockedID);
	
	boolean existsByBlockingUserAndBlockedUser(User blockingUser, User blockedUser);
	boolean existsByBlockingIDAndBlockedID(Integer blockingID, Integer blockedID);
	
}

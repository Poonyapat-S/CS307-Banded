package com.javainuse.classes;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BlockService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserFollowerRepository userFollowerRepository;
	@Autowired
	private BlockRepository blockRepository;
	
	//runs a check for both variations of whether a block exists given two userIDs
	public boolean blockExists(Integer userID1, Integer userID2) {
		if (blockRepository.existsByBlockingIDAndBlockedID(userID1, userID2)) {
			return true;
		}
		return blockRepository.existsByBlockingIDAndBlockedID(userID2, userID1);
	}
	
	//retrieve all blocks associated with the given user, whether they are blocking or blocked
	public List<User> getUsersWithBlocks(User user) {
		List<Block> blockingObjects;
		List<Block> blockedObjects;
		List<User> users = new ArrayList<>();
		try {
			blockingObjects = blockRepository.findByBlockingID(user.getUserID());
			blockedObjects = blockRepository.findByBlockedID(user.getUserID());
		} catch (Exception e) {
			System.out.println("blockService.getUsersWithBlocks error when retrieving Block objects");
			blockingObjects = new ArrayList<>();
			blockedObjects = new ArrayList<>();
		}
		if (blockingObjects.size() > 0) {
			for (Block b : blockingObjects) {
				try {
					//attempt to grab each User via blockedID from the retrieved Block objects from blockingObjects
					Optional<User> blockedUser = userRepository.findByUserID(b.getBlockedID());
					if (blockedUser.isPresent()) {
						users.add(blockedUser.get()); //if the Optional object actually holds a user, add it to users List
					} else {
						System.out.println("Empty User object returned in BlockService.getUsersWithBlocks (retrieving blocked User)");
					}
				} catch (Exception e) {
					System.out.println("findByUserID failure in BlockService.getUsersWithBlocks (retrieving blocked User)");
				}
			}
		}
		if (blockedObjects.size() > 0) {
			for (Block b : blockedObjects) {
				try {
					//attempt to grab each User via blockedID from the retrieved Block objects from blockingObjects
					Optional<User> blockingUser = userRepository.findByUserID(b.getBlockingID());
					if (blockingUser.isPresent()) {
						users.add(blockingUser.get()); //if the Optional object actually holds a user, add it to users List
					} else {
						System.out.println("Empty User object returned in BlockService.getUsersWithBlocks (retrieving blocking User)");
					}
				} catch (Exception e) {
					System.out.println("findByUserID failure in BlockService.getUsersWithBlocks (retrieving blocking User");
				}
			}
		}
		return users;
	}
}

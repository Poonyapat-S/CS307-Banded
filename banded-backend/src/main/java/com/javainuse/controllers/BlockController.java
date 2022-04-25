package com.javainuse.controllers;

import com.javainuse.classes.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/blockcontrol")
public class BlockController {
	@Autowired
	private UserRepository userRepository;
	private UserFollowerRepository userFollowerRepository;
	private BlockRepository blockRepository;
	private FollowController followController;
	
	@PostMapping(path = "/blockuser")
	public ResponseEntity<String> blockUser(@AuthenticationPrincipal User currUser, @RequestBody String blockUsername) {
		if (!userRepository.existsByUserName(blockUsername)) {
			//if the username cannot be found, return an error
			return ResponseEntity.status(HttpStatus.CONFLICT).body("BLOCK FAILURE: User with userName [" + blockUsername + "] not found");
		}
		
		//make sure the user exists and try to create block object
		User blockedUser;
		try {
			blockedUser = userRepository.findByUserName(blockUsername).orElseThrow(Exception::new);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("BLOCK FAILURE: empty Optional object returned when retrieving data with userName + " +blockUsername);
		}
		
		Block newBlock;
		if (blockRepository.existsByBlockingUserAndBlockedUser(currUser, blockedUser)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("BLOCK FAILURE: block already exists");
		} else {
			newBlock = new Block(currUser, blockedUser);
		}
		
		//blocking should remove a follow, so check for if a follow exists from both sides and if so unfollow
		if (userFollowerRepository.existsByFollowingUserAndFollowedUser(currUser, blockedUser)) {
			followController.unfollowUser(currUser, blockUsername);
		}
		if (userFollowerRepository.existsByFollowingUserAndFollowedUser(blockedUser, currUser)) {
			followController.unfollowUser(blockedUser, currUser.getUsername());
		}
		
		//block data does not already exist and both users are valid, so new row creation
		blockRepository.save(newBlock);
		System.out.println(currUser.getUsername() + " blocked " + blockUsername);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(path = "/unblockuser")
	public ResponseEntity<String> unblockUser(@AuthenticationPrincipal User currUser, @RequestBody String unblockUsername) {
		if (!userRepository.existsByUserName(unblockUsername)) {
			//if the username cannot be found, return an error
			return ResponseEntity.status(HttpStatus.CONFLICT).body("UNBLOCK FAILURE: User with userName [" + unblockUsername + "] not found");
		}
		
		//establish User object from given userName to create Block object for deletion
		User unblockedUser;
		try {
			unblockedUser = userRepository.findByUserName(unblockUsername).orElseThrow(Exception::new);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("UNBLOCK FAILURE: empty Optional object returned when retrieving data with userName + " +unblockUsername);
		}
		
		//make sure the block connection already exists before trying to delete it
		Block rowToBeDeleted;
		if (!blockRepository.existsByBlockingUserAndBlockedUser(currUser, unblockedUser)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("UNBLOCK FAILURE: no row deleted bc + " +
					currUser.getUsername() + " (userID:" + currUser.getUserID() + ") has not blocked " +
					unblockUsername + " (userID:" + unblockedUser.getUserID() + ")");
		} else {
			//grab the exact Block object to be deleted
			rowToBeDeleted = blockRepository.findByBlockingUserAndBlockedUser(currUser, unblockedUser).orElseThrow();
		}
		
		//delete the "block link" between users
		blockRepository.delete(rowToBeDeleted);
		System.out.println("Successfully unblocked [" + unblockUsername + "]");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

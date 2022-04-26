package com.javainuse.controllers;

import com.javainuse.classes.*;
import com.javainuse.controllers.request.userInfoRequest;
import com.javainuse.controllers.request.topicInfoRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/followcontrol")
public class FollowController {
	@Autowired
	private UserRepository userRepository;
	private TopicRepository topicRepository;
	private UserFollowerRepository userFollowerRepository;
	private TopicFollowerRepository topicFollowerRepository;
	
	
	/* -=- USER-RELATED FUNCTIONS -=- */
	@PostMapping(path = "/followuser")
	public ResponseEntity<String> followUser(@AuthenticationPrincipal User currUser, @RequestBody String newFollow) {
		Integer followingID = currUser.getUserID();
		User followedUser;
		
		//attempt to retrieve ID of who is being followed by retrieving User Object via given userName
		try {
			followedUser = userRepository.findByUserName(newFollow).orElseThrow(Exception::new);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("FOLLOW FAILURE: User with userName [" + newFollow + "] not found");
		}
		Integer followedID = followedUser.getUserID();
		
		//check that this row does not already exist
		if (userFollowerRepository.existsByFollowingIDAndFollowedID(followingID, followedID)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("FOLLOW FAILURE: current user already follows [" + newFollow + "]");
		}
		
		
		//follow data does not already exist and both users are valid, so insert new row in UserFollower table
		UserFollower newUserFollower = new UserFollower(currUser, followedUser);
		userFollowerRepository.save(newUserFollower);
		System.out.println(currUser.getUsername() + " (userID:" + followingID + ") followed " + newFollow
			+ " (userID:" + followedID + ")");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(path = "/unfollowuser")
	public ResponseEntity<String> unfollowUser(@AuthenticationPrincipal User currUser, @RequestBody String unfollowedUserName) {
		if (!userRepository.existsByUserName(unfollowedUserName)) {
			//if the username cannot be found, return an error
			return ResponseEntity.status(HttpStatus.CONFLICT).body("UNFOLLOW FAILURE: User with userName [" + unfollowedUserName + "] not found");
		}
		
		//establish User object from given userName to create UserFollower object for deletion
		User unfollowedUser;
		try {
			unfollowedUser = userRepository.findByUserName(unfollowedUserName).orElseThrow(Exception::new);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("UNFOLLOW FAILURE: empty Optional object returned when retrieving data with userName + " +unfollowedUserName);
		}
		
		//make sure the follower connection already exists before trying to delete it
		UserFollower rowToBeDeleted;
		if (!userFollowerRepository.existsByFollowingUserAndFollowedUser(currUser, unfollowedUser)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("UNFOLLOW FAILURE: no row deleted bc + " +
					currUser.getUsername() + " (userID:" + currUser.getUserID() + ") was not following " +
					unfollowedUserName + " (userID:" + unfollowedUser.getUserID() + ")");
		} else {
			//grab the exact UserFollowerObject to be deleted
			rowToBeDeleted = userFollowerRepository.findByFollowingUserAndFollowedUser(currUser, unfollowedUser).orElseThrow();
		}
		
		//delete the "follow link" between users
		userFollowerRepository.delete(rowToBeDeleted);
		System.out.println("Successfully unfollowed [" + unfollowedUserName + "]");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path = "/getfollowedusers")
	public List<String> getFollowedUserNames(@AuthenticationPrincipal User currUser) {
		List<UserFollower> userFollowerObjects = userFollowerRepository.findByFollowingID(currUser.getUserID());
		List<String> usersFollowed = new ArrayList<>();
		for (UserFollower uf : userFollowerObjects) {
			try {
				User u = userRepository.findByUserID(uf.getFollowedID()).orElseThrow();
				usersFollowed.add(u.getUsername());
			} catch (Exception e) {
				System.out.println("Error finding User with userID: [" + uf.getFollowedID() + "]");
			}
		}
		return usersFollowed;
	}
	
	
	/* -=-TOPIC-RELATED FUNCTIONS-=- */
	@PostMapping(path = "/followtopic")
	public ResponseEntity<String> followTopic(@AuthenticationPrincipal User currUser, @RequestBody String newFollow) {
		System.out.println("Follow Topic Called: "+newFollow);
		if (!topicRepository.existsByTopicName(newFollow)) {
			//if the topic cannot be found in the Topic database (checking by name), throw an exception
			return ResponseEntity.status(HttpStatus.CONFLICT).body("FOLLOW FAILURE: Topic with topicName [" + newFollow + "] not found");
		}
		
		Topic followedTopic;
		try {
			followedTopic = topicRepository.findByTopicName(newFollow).orElseThrow(Exception::new);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("FOLLOW FAILURE: idk how this happened but some " +
					"sort of Optional<> issue with Topic with topicName [" + newFollow + "]");
		}
		
		//check if this follow has already been stored
		if (topicFollowerRepository.existsByUserAndTopic(currUser, followedTopic)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("FOLLOW FAILURE: current user already follows Topic [" + newFollow + "]");
		}
		
		
		//follow data does not already exist and current user and given topicName are valid, so insert new row in TopicFollower table
		TopicFollower newTopicFollow = new TopicFollower(currUser, followedTopic);
		topicFollowerRepository.save(newTopicFollow);
		System.out.println(currUser.getUsername() + " (userID:" + currUser.getUserID() + ") followed " + newFollow
				+ " (topicID:" + followedTopic.getTopicID() + ")");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PostMapping(path = "/unfollowtopic")
	public ResponseEntity<String> unfollowTopic(@AuthenticationPrincipal User currUser, @RequestBody String unfollowedTopicName) {
		if (!topicRepository.existsByTopicName(unfollowedTopicName)) {
			//if the topic name cannot be found, return an error
			return ResponseEntity.status(HttpStatus.CONFLICT).body("UNFOLLOW FAILURE: Topic with topicName [" + unfollowedTopicName + "] not found");
		}
		
		//establish Topic object from given topic name to create TopicFollower object for deletion
		Topic unfollowedTopic;
		try {
			unfollowedTopic = topicRepository.findByTopicName(unfollowedTopicName).orElseThrow(Exception::new);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("UNFOLLOW FAILURE: empty Optional object returned when retrieving data with topic + " + unfollowedTopicName);
		}
		
		//make sure the follower connection already exists before trying to delete it
		TopicFollower rowToBeDeleted;
		if (!topicFollowerRepository.existsByUserAndTopic(currUser, unfollowedTopic)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("UNFOLLOW FAILURE: no row deleted bc + " +
					currUser.getUsername() + " (userID:" + currUser.getUserID() + ") was not following " +
					unfollowedTopicName + " (topicID:" + unfollowedTopic.getTopicID() + ")");
		} else {
			//retrieve the exact instance of this follow to be deleted
			rowToBeDeleted = topicFollowerRepository.findByUserAndTopic(currUser, unfollowedTopic).orElseThrow();
		}
		
		//delete the "follow link" between user and given topic
		topicFollowerRepository.delete(rowToBeDeleted);
		System.out.println("Successfully unfollowed [" + unfollowedTopicName + "]");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path = "/getfollowedtopics")
	public List<Topic> getFollowedTopics(@AuthenticationPrincipal User currUser) {
		List<TopicFollower> topicFollowerObjects = topicFollowerRepository.findByUser(currUser);
		List<Topic> topicsFollowed = new ArrayList<>();
		for (TopicFollower tf : topicFollowerObjects) {
			try {
				topicsFollowed.add(topicRepository.findByTopicID(tf.getTopicID()).orElseThrow());
			} catch (Exception e) {
				System.out.println("Error finding topic with topicID: [" + tf.getTopicID() + "]");
			}
		}
		return topicsFollowed;
	}

	@GetMapping(path="/topic/isFollowing/{topicID}")
	public Boolean getIsFollowing(@AuthenticationPrincipal User user, @PathVariable Integer topicID) {
		return topicFollowerRepository.existsByUserAndTopic(user, topicRepository.findByTopicID(topicID).orElse(null));
	}
}

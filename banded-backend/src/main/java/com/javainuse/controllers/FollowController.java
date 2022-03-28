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
	
	@PostMapping(path = "/followuser")
	public ResponseEntity<String> followUser(@AuthenticationPrincipal User currUser, @RequestBody String newFollow) {
		Integer followingID = currUser.getUserID();
		User followedUser;
		
		//attempt to retrieve ID of who is being followed by retrieving User Object via given userName
		try {
			followedUser = userRepository.findByUserName(newFollow).orElseThrow(() -> new Exception());
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
	
	@PostMapping(path = "/followtopic")
	public ResponseEntity<String> followTopic(@AuthenticationPrincipal User currUser, @RequestBody String newFollow) {
		if (!topicRepository.existsByTopicName(newFollow)) {
			//if the topic cannot be found in the Topic database (checking by name), throw an exception
			return ResponseEntity.status(HttpStatus.CONFLICT).body("FOLLOW FAILURE: Topic with topicName [" + newFollow + "] not found");
		}
		
		Topic followedTopic = new Topic();
		try {
			followedTopic = topicRepository.findByTopicName(newFollow).orElseThrow(() -> new Exception());
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
}

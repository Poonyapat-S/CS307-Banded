package com.javainuse.controllers;

import com.javainuse.classes.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/interaction")
public class PostInteractionController {
	@Autowired
	private UserRepository userRepository;
	private PostRepository postRepository;
	private ReactionRepository reactionRepository;
	private SavedPostRepository savedPostRepository;
	
	/* -=- REACTION-RELATED FUNCTIONS -=- */
	@PostMapping(path = "/like")
	public ResponseEntity<String> likePost(@AuthenticationPrincipal User currUser, @RequestBody Integer postID) {
		Post likedPost = new Post();
		//retrieve Post with given postID
		try {
			likedPost = postRepository.findByPostID(postID).orElseThrow();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("REACTION FAILURE: Post with postID [" + postID +
					"] could not be found");
		}
		
		//check that this Reaction has not already been stored
		if (reactionRepository.existsByPostAndUser(likedPost, currUser)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("REACTION FAILURE: " + currUser.getUsername() +
					" has already liked Post with postID [" + postID + "]");
		}
		
		//create and store new Reaction object
		Reaction newLike = new Reaction(likedPost, currUser);
		reactionRepository.save(newLike);
		System.out.println(currUser.getUsername() + " (userID:" + currUser.getUserID() + ") liked Post with postID ["
			+ postID + "]");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(path = "/unlike")
	public ResponseEntity<String> unlikePost(@AuthenticationPrincipal User currUser, @RequestBody Integer postID) {
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

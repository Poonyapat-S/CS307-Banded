package com.javainuse.controllers;

import com.javainuse.classes.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/interaction")
public class PostInteractionController {
	@Autowired
	private UserRepository userRepository;
	private PostRepository postRepository;
	private ReactionRepository reactionRepository;
	private SavedPostRepository savedPostRepository;
	
	/* -=- REACTION-RELATED FUNCTIONS -=- */
	@PostMapping(path = "/like")
	public ResponseEntity<String> likePost(@AuthenticationPrincipal User currUser, @RequestBody Integer postID) {
		Post likedPost;
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
		Post unlikedPost;
		//retrieve Post with given postID
		try {
			unlikedPost = postRepository.findByPostID(postID).orElseThrow();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("REACTION FAILURE: Post with postID [" + postID +
					"] could not be found");
		}
		
		//check that this Reaction already exists in database
		Reaction unlike;
		if (!reactionRepository.existsByPostAndUser(unlikedPost, currUser)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("REACTION FAILURE: " + currUser.getUsername() +
					" has not liked Post with postID [" + postID + "]");
		} else {
			//retrieve instance of Reaction from database to be deleted
			unlike = reactionRepository.findByPostAndUser(unlikedPost, currUser).orElseThrow();
		}
		
		//delete retrieved instance from Reaction database
		reactionRepository.delete(unlike);
		System.out.println(currUser.getUsername() + " (userID:" + currUser.getUserID() + ") unliked Post with postID ["
				+ postID + "]");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path = "/getlikestatus/{postID}")
	public boolean isPostLiked(@AuthenticationPrincipal User currUser, @PathVariable Integer postID) {
		return reactionRepository.existsByUserAndPostID(currUser, postID);
	}
	
	
	/* -=- SAVED POST-RELATED FUNCTIONS -=- */
	@PostMapping(path = "/savepost")
	public ResponseEntity<String> savePost(@AuthenticationPrincipal User currUser, @RequestBody Integer postID) {
		Post savedPost;
		//retrieve Post with given postID
		try {
			savedPost = postRepository.findByPostID(postID).orElseThrow();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("SAVE POST FAILURE: Post with postID [" + postID +
					"] could not be found");
		}
		
		//check that this Reaction has not already been stored
		if (savedPostRepository.existsByPostAndUser(savedPost, currUser)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("SAVE POST FAILURE: " + currUser.getUsername() +
					" has already liked Post with postID [" + postID + "]");
		}
		
		//create and store new Reaction object
		SavedPost newSave = new SavedPost(savedPost, currUser);
		savedPostRepository.save(newSave);
		System.out.println(currUser.getUsername() + " (userID:" + currUser.getUserID() + ") saved Post with postID ["
				+ postID + "]");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(path = "/unsavepost")
	public ResponseEntity<String> unsavePost(@AuthenticationPrincipal User currUser, @RequestBody Integer postID) {
		Post unsavedPost;
		//retrieve Post with given postID
		try {
			unsavedPost = postRepository.findByPostID(postID).orElseThrow();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("UNSAVE POST FAILURE: Post with postID [" + postID +
					"] could not be found");
		}
		
		//check that this SavedPost already exists in database
		SavedPost unsave;
		if (!savedPostRepository.existsByPostAndUser(unsavedPost, currUser)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("UNSAVE POST FAILURE: " + currUser.getUsername() +
					" has not liked Post with postID [" + postID + "]");
		} else {
			//retrieve instance of SavedPost to be deleted
			unsave = savedPostRepository.findByPostAndUser(unsavedPost, currUser).orElseThrow();
		}
		
		//delete retrieved SavedPost object from SavedPost database
		savedPostRepository.delete(unsave);
		System.out.println(currUser.getUsername() + " (userID:" + currUser.getUserID() + ") unsaved Post with postID ["
				+ postID + "]");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path = "/getsavestatus/{postID}")
	public boolean isPostSaved(@AuthenticationPrincipal User currUser, @PathVariable Integer postID) {
		return savedPostRepository.existsByUserAndPostID(currUser, postID);
	}
}

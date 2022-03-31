package com.javainuse.classes;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class PostInteractionService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReactionRepository reactionRepository;
	@Autowired
	private SavedPostRepository savedPostRepository;
	
	public List<Post> retrieveLikedPosts(Integer userID) {
		List<Post> likedPosts = new ArrayList<>();
		
		return likedPosts;
	}
}

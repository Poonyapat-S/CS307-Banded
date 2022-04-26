package com.javainuse.classes;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> retrieveUserPostInteractions(Integer userID) {
		List<Reaction> likedPosts;
		List<Post> replies;
		List<SavedPost> savedPosts;
		List<Post> recentInteractions = new ArrayList<>();
		User user;
		try {
			user = userRepository.findByUserID(userID).orElseThrow();
		} catch (Exception e) {
			System.out.println("ERROR retrieving user in PostInteractionService with userID: ["+userID+"]");
			return recentInteractions;
		}
		
		//retrieve all Reaction objects (liked posts) for given user
		try {
			likedPosts = reactionRepository.findByUserID(userID);
		} catch (Exception e) {
			System.out.println("ERROR retrieving liked posts from User [" + user.getUsername() + "]");
			likedPosts  = new ArrayList<>();
		}
		
		//retrieve all replies that given user has made
		try {
			replies = postRepository.findByUserAndParentPostIDIsNotNull(user);
		} catch (Exception e) {
			System.out.println("ERROR retrieving replies from User ["+user.getUsername()+"]");
			replies = new ArrayList<>();
		}
		
		//retrieve all SavedPost objects from given user
		try {
			savedPosts = savedPostRepository.findByUser(user);
		} catch (Exception e) {
			System.out.println("ERROR retrieving saved posts from User ["+user.getUsername()+"]");
			savedPosts = new ArrayList<>();
		}
		
		if (likedPosts.size() > 0) {
			for (Reaction lp : likedPosts) {
				try {
					Post p = lp.getPost();
					p.setInteractionType("Liked");
					p.setInteractionTime(lp.getReactionTime());
					recentInteractions.add(p);
				} catch (Exception e) {
					System.out.println("ERROR retrieving a liked post from Reaction in PostInteractionService");
				}
			}
		}
		if (replies.size() > 0) {
			for (Post rep : replies) {
				try {
					Post p = postRepository.findByPostID(rep.getParentPostID()).orElseThrow();
					p.setInteractionType("Liked");
					if (recentInteractions.contains(p)) {
						p.setInteractionType("Liked and Replied");
					} else {
						p.setInteractionType("Replied");
					}
					p.setInteractionTime(rep.getPostTime());
					recentInteractions.add(p);
				} catch (Exception e) {
					System.out.println("ERROR retrieving a replied post in PostInteractionService");
				}
			}
		}
		if (savedPosts.size() > 0) {
			for (SavedPost sp : savedPosts) {
				try {
					Post p = sp.getPost();
					Post checkPost = p;
					checkPost.setInteractionType("Liked");
					if (recentInteractions.contains(checkPost)) {
						p.setInteractionType("Liked and Saved");
					}
					checkPost.setInteractionType("Replied");
					if (recentInteractions.contains(checkPost)) {
						p.setInteractionType("Saved and Replied");
					}
					checkPost.setInteractionType("Liked and Replied");
					if (recentInteractions.contains(checkPost)) {
						p.setInteractionType("Liked, Saved, and Replied");
					} else {
						p.setInteractionType("Saved");
					}
					p.setInteractionTime(sp.getSavedTime());
					recentInteractions.add(p);
				} catch (Exception e) {
					System.out.println("ERROR retrieving a saved post in PostInteractionService");
				}
			}
		}
		
		recentInteractions = sortByPostInteractionTime(recentInteractions);
		
		return recentInteractions;
	}
	
	public List<Post> sortByPostInteractionTime(List<Post> interactions) {
		Comparator<Post> dateComparator = Comparator.comparing(Post::getInteractionTime);
		Collections.sort(interactions,dateComparator);
		Collections.reverse(interactions);
		
		return interactions;
	}
}

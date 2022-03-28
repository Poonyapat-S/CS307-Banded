package com.javainuse.classes;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class FollowService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private UserFollowerRepository userFollowerRepository;
	
	public List<User> retrieveFollowedUsers(Integer userID) {
		List<UserFollower> userFollowerObjects = new ArrayList<>();
		List<User> users = new ArrayList<User>();
		try {
			//retrieve all rows from UserFollower table as a List of UserFollower objects
			userFollowerObjects = userFollowerRepository.findByFollowingID(userID);
		} catch (Exception e) {
			System.out.println("userFollowerRepository.findByFollowingID error in FollowService.findByFollowingID");
			userFollowerObjects = new ArrayList<>();
		}
		if (userFollowerObjects.size() > 0) {
			for (UserFollower u : userFollowerObjects) {
				try {
					//call findByUserID for each followedID from the retrieved UserFollower object
					users.add(userRepository.findByUserID(u.getFollowedID()).orElseThrow(() -> (new UsernameNotFoundException(String.format("", "")))));
				} catch (Exception e) {
					System.out.println("findByUserID failure in FollowService.retrieveFollowedUsers");
				}
			}
		}
		return users;
	}
	
}

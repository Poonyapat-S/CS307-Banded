package com.javainuse.controllers;

import com.javainuse.classes.*;
import com.javainuse.controllers.request.newDMRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/dms")
public class DirectMessageController {
	@Autowired
	private UserRepository userRepository;
	private DirectMessageRepository dmRepository;
	private DirectMessageService dmService;
	
	@PostMapping (path = "/send")
	public ResponseEntity<?> sendDM(@AuthenticationPrincipal User currUser, @RequestBody newDMRequest request) {
		DirectMessage newDM = new DirectMessage();
		newDM.setSender(currUser);
		newDM.setMessageText(request.getMessageText());
		User otherUser;
		try {
			otherUser = userRepository.findByUserName(request.getOtherUserName()).orElseThrow();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("DM FAILURE: could not find User with username of ["+request.getOtherUserName()+"]");
		}
		newDM.setRecipient(otherUser);
		newDM.setTimeSent(LocalDateTime.now());
		dmRepository.save(newDM);
		System.out.println("DM sent from "+currUser.getUsername()+" to "+otherUser.getUsername());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping(path = "/conversations")
	public List<Conversation> getConversations(@AuthenticationPrincipal User currUser) {
		List<Conversation> conversations = new ArrayList<>();
		List<DirectMessage> allDMs = new ArrayList<>();
		
		try {
			allDMs = dmRepository.findBySenderOrRecipient(currUser, currUser);
		} catch (Exception e) {
			System.out.println("ERROR finding conversations associated with ["+currUser.getUsername()+"]");
		}
		if (allDMs.size() > 0) {
			conversations = dmService.organizeConversations(allDMs, currUser);
		}

		return conversations;
	}
}

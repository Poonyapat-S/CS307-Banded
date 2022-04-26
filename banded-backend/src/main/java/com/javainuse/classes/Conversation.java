package com.javainuse.classes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Conversation {
	private User currUser;
	private User otherUser;
	private List<DirectMessage> dms = new ArrayList<>();
	private LocalDateTime latestMessageTime;
	
	public Conversation(User currUser, User otherUser, List<DirectMessage> dms, LocalDateTime latestMessageTime) {
		this.currUser = currUser;
		this.otherUser = otherUser;
		this.dms = dms;
		this.latestMessageTime = latestMessageTime;
	}
	public Conversation(User currUser, User otherUser, DirectMessage dm) {
		this.currUser = currUser;
		this.otherUser = otherUser;
		this.dms.add(dm);
		this.latestMessageTime = dm.getTimeSent();
	}
}

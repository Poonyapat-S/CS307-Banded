package com.javainuse.classes;

import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class DirectMessageService {
	@Autowired
	private UserRepository userRepository;
	private DirectMessageRepository dmRepository;
	
	public List<Conversation> organizeConversations(List<DirectMessage> userDMs, User currUser) {
		List<Conversation> conversations = new ArrayList<>();
		
		for (DirectMessage dm : userDMs) {
			User otherUser = new User();
			otherUser.setUserName("bitchboi");
			//determine whether we need to retrieve otherUser via recipient or sender ID and do so
			if (Objects.equals(dm.getSenderID(), currUser.getUserID())) {
				try {
					otherUser = (User) Hibernate.unproxy(dm.getRecipient());
				} catch (Exception e) {
					System.out.println("Unable to find User with userID of ["+dm.getRecipientID()+"]");
					continue;
				}
			} else {
				try {
					otherUser = (User) Hibernate.unproxy(dm.getSender());
				} catch (Exception e) {
					System.out.println("Unable to find User with userID of ["+dm.getSenderID()+"]");
					continue;
				}
			}
			
			//sort the DM into the proper Conversation by checking whether the correct one exists, and creating a new Conversation if necessary
			int index = -1;
			for (int i = 0; i < conversations.size(); i++) {
				if (conversations.get(i).getOtherUser().getUsername().equals(otherUser.getUsername())) {
					index = i;
					break;
				}
			}
			if (index >= 0) {
				conversations.get(index).getDms().add(dm);
			} else {
				Conversation c = new Conversation(currUser, otherUser, dm);
				conversations.add(c);
			}
		}
		
		//organize the DMs in each conversation according to timeSent, and set latestMessageTime once sorted
		for (Conversation c : conversations) {
			organizeByDirectMessageTimes(c.getDms());
			c.setLatestMessageTime(c.getDms().get(0).getTimeSent());
		}
		
		//organize conversations by their latestMessageTime now that those have been properly sent
		organizeByLatestMessage(conversations);
		
		return conversations;
	}
	
	public void organizeByLatestMessage(List<Conversation> convos) {
		Comparator<Conversation> dateComparator = Comparator.comparing(Conversation::getLatestMessageTime);
		Collections.sort(convos,dateComparator);
		Collections.reverse(convos);
	}
	
	public void organizeByDirectMessageTimes(List<DirectMessage> dms) {
		Comparator<DirectMessage> dateComparator = Comparator.comparing(DirectMessage::getTimeSent);
		Collections.sort(dms,dateComparator);
		Collections.reverse(dms);
	}
}

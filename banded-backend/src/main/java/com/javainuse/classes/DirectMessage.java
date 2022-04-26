package com.javainuse.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="dm")
public class DirectMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dmID")
	private Integer dmID;
	
	@JsonIgnore
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name="senderID")
	private User sender;
	@Column(name = "senderID", updatable = false, insertable = false)
	private Integer senderID;
	
	@JsonIgnore
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name="recipientID")
	private User recipient;
	@Column(name = "recipientID", updatable = false, insertable = false)
	private Integer recipientID;
	
	@Column(name = "messageText")
	private String messageText;
	
	@Column(name = "timeSent")
	private LocalDateTime timeSent;
	
	public DirectMessage(User sender, User recipient, String messageText) {
		this.sender = sender;
		this.recipient = recipient;
		this.messageText = messageText;
		this.timeSent = LocalDateTime.now();
	}
	public DirectMessage(User sender, User recipient, String messageText, LocalDateTime timeSent) {
		this.sender = sender;
		this.recipient = recipient;
		this.messageText = messageText;
		this.timeSent = timeSent;
	}
}

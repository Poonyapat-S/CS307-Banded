package com.javainuse.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "block")
public class Block {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="blockID")
	private Integer blockID;
	
	@JsonIgnore
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name="blockerUserID")
	private User blockingUser;
	@Column(name = "blockerUserID", updatable = false, insertable = false)
	private Integer blockingID;
	
	@JsonIgnore
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name="blockedUserID")
	private User blockedUser;
	@Column(name = "blockedUserID", updatable = false, insertable = false)
	private Integer blockedID;
	
	public Block(User blockingUser, User blockedUser) {
		this.blockingUser = blockingUser;
		this.blockedUser = blockedUser;
	}
}

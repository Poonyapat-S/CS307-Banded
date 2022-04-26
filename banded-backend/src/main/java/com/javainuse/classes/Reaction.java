package com.javainuse.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "reaction")
public class Reaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reactionID")
	private Integer reactionID;
	
	@JsonIgnore
	@ManyToOne(targetEntity = Post.class, fetch = FetchType.LAZY)
	@JoinColumn(name="postID")
	private Post post;
	@Column(name = "postID", updatable = false, insertable = false)
	private Integer postID;
	
	@JsonIgnore
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name="userID")
	private User user;
	@Column(name = "userID", updatable = false, insertable = false)
	private Integer userID;
	
	@Column(name = "reactionTime")
	private LocalDateTime reactionTime;
	
	public Reaction(Post post, User user) {
		this.post = post;
		this.user = user;
	}
	public Reaction(Integer postID, Integer userID) {
		this.postID = postID;
		this.userID = userID;
	}
}

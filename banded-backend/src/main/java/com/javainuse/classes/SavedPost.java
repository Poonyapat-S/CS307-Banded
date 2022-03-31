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
@Table(name = "savedpost")
public class SavedPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="savedPostID")
	private Integer savedPostID;
	
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
	
	public SavedPost (Post post, User user) {
		this.post = post;
		this.user = user;
	}
	public SavedPost(Integer postID, Integer userID) {
		this.postID = postID;
		this.userID = userID;
	}
}

package com.javainuse.classes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "userfollower")
public class UserFollower {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userFollowerID")
	private Integer userFollowerID;
	
	@JsonIgnore
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name="userID")
	private User followingUser;
	@Column(name = "userID", updatable = false, insertable = false)
	private Integer followingID;
	
	@JsonIgnore
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name="followerID")
	private User followedUser;
	@Column(name = "followerID", updatable = false, insertable = false)
	private Integer followedID;
	
	public UserFollower(User followingUser, User followedUser) {
		this.followingUser = followingUser;
		this.followedUser = followedUser;
	}
	public UserFollower(Integer followingID, Integer followedID) {
		this.followingID = followingID;
		this.followedID = followedID;
	}
}

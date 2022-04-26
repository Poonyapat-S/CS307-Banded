package com.javainuse.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "topicfollower")
public class TopicFollower {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="topicFollowerID")
	private Integer topicFollowerID;
	
	@JsonIgnore
	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name="userID")
	private User user;
	@Column(name = "userID", updatable = false, insertable = false)
	private Integer userID;

	@JsonIgnore
	@ManyToOne(targetEntity = Topic.class, fetch = FetchType.EAGER)
	@JoinColumn(name="topicID")
	private Topic topic;
	@Column(name = "topicID", updatable = false, insertable = false)
	private Integer topicID;
	
	public TopicFollower(User user, Topic topic) {
		this.user = user;
		this.topic = topic;
	}
	public TopicFollower(Integer userID, Integer topicID) {
		this.userID = userID;
		this.topicID = topicID;
	}
}

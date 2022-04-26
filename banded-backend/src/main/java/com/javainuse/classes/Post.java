package com.javainuse.classes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="postID")
    private Integer postID;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="userID")
    private User user;

    @Column(name = "userID", updatable = false, insertable = false)
    private Integer userID;

    @Column(name = "postTitle")
    private String postTitle;

    @Column(name = "postText")
    private String postText;

    @Column(name = "timePosted")
    private LocalDateTime postTime;

    @JsonBackReference
    @ManyToOne(targetEntity = Post.class, fetch=FetchType.LAZY)
    @JoinColumn(name="parentPostID")
    private Post parentPost;

    @Column(name = "parentPostID", updatable = false, insertable = false)
    private Integer parentPostID;

    @JsonIgnore
    @ManyToOne(targetEntity = Topic.class, fetch = FetchType.LAZY)
    @JoinColumn(name="topicID")
    private Topic topic;

    @Column(name = "topicID", insertable = false, updatable = false)
    private Integer topicID;

    @Column(name="isAnon")
    private Boolean isAnon;

    @Transient
    private String topicName;

    @Transient
    private String userName;

    public Post(User user, Post parentPost, Topic topic, String postTitle, String postText, Boolean isAnon) {
        this.user = user;
        this.parentPost = parentPost;
        this.topic = topic;
        this.postTitle = postTitle;
        this.postText = postText;
        this.isAnon = isAnon;
    }

    public Post(User user, Post parentPost, String postTitle, Topic topic, String postText) {
        this.user = user;
        this.parentPost = parentPost;
        this.topic = topic;
        this.postTitle = postTitle;
        this.postText = postText;
        this.isAnon = false;
    }
}

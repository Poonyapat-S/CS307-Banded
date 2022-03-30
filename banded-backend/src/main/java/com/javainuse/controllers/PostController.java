package com.javainuse.controllers;

import com.javainuse.classes.*;
import com.javainuse.controllers.request.newPostRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;
    private TopicRepository topicRepository;
    private UserRepository userRepository;
    private PostService postService;

    @GetMapping
    public List<Post> timeline(@AuthenticationPrincipal User user) {
        return postRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> post(@AuthenticationPrincipal User user, @RequestBody newPostRequest request) {
        Post newPost = new Post();
        newPost.setPostText(request.getPostText());
        System.out.println("Topic: "+request.getTopicName());
        Topic topic;
        try {
            topic = topicRepository.findByTopicName(request.getTopicName()).orElseThrow(() -> new Exception());
        }
        catch(Exception e) {
            topic = new Topic(request.getTopicName());
            System.out.println(topic.getTopicName());
            topicRepository.save(topic);
        }
        newPost = new Post(user, null, topic, request.getPostTitle(), request.getPostText(), request.getIsAnon());
        System.out.println(user.getUserID());
        postRepository.save(newPost);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping(path="/user/{userName}")
    public List<Post> getPostsUser(@AuthenticationPrincipal User user, @PathVariable String userName)
    {
        if(user.getUsername() == userName) return postRepository.findByUser(user);
        try {
            User foundUser = userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException(String.format("", "")));
            return postRepository.findByUserAndIsAnonFalse(foundUser);
        }
        catch(Exception e) {
            return new ArrayList<Post>();
        }
    }

    @GetMapping(path="/topic/{topicName}")
    public List<Post> getPostInTopic(@AuthenticationPrincipal User user, @PathVariable String topicName)
    {
        try {
           Topic foundTopic = topicRepository.findByTopicName(topicName).orElseThrow(() -> new Exception());
           List<Post> postList = postRepository.findByTopic(foundTopic);
           return postService.anonymizeForTopics(postList);
        }
        catch(Exception e) {
            return new ArrayList<Post>();
        }
    }

    @PostMapping(path="/{postId}")
    public ResponseEntity<?> replyPost(@AuthenticationPrincipal User user, @PathVariable Integer postId, @RequestBody newPostRequest request) {
        try {
            Post parentPost = postRepository.findById(postId).orElseThrow(() -> new Exception());
            Topic topic = parentPost.getTopic();
            Post newPost = new Post(user, parentPost, topic, request.getPostTitle(), request.getPostText(), request.getIsAnon());
            postRepository.save(newPost);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/{postId}")
    public Post getPost(@AuthenticationPrincipal User user, @PathVariable Integer postId) {
        try {
            Post parentPost = postRepository.findById(postId).orElseThrow(() -> new Exception());
            return parentPost;
        }
        catch (Exception e){
            return null;
        }
    }
}


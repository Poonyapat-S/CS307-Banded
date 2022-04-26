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

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.min;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;
    private TopicRepository topicRepository;
    private UserRepository userRepository;
    private FollowService followService;
    private PostService postService;

    @GetMapping("/guest/timeline")
    public List<Post> guestTimeline() {
        List<Post> posts = postRepository.findAll();
        for(Post post: posts) {
            post.setTopicName(post.getTopic().getTopicName());
            if(post.getIsAnon()){
                post.setUserName("Anonymous");
            }
            else{
                post.setUserName(post.getUser().getUsername());
            }
        }
        postService.sortByDateTimeDesc(posts);
        return posts;
    }
    @GetMapping("/guest/topics")
    public List<Topic> getTopicList(){
        List<Topic> allTopics = topicRepository.findAll();
        return allTopics;
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
        newPost.setPostTime(LocalDateTime.now());
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
            List<Post> toReturn = postRepository.findByUserAndIsAnonFalse(foundUser);
            for(Post post: toReturn) {
                post.setTopicName(post.getTopic().getTopicName());
            }
            return toReturn;
        }
        catch(Exception e) {
            return new ArrayList<Post>();
        }
    }

    @GetMapping(path="/topic/{topicID}")
    public List<Post> getPostInTopic(@AuthenticationPrincipal User user, @PathVariable Integer topicID, @RequestParam int count)
    {
        try {
           Topic foundTopic = topicRepository.findByTopicID(topicID).orElseThrow(() -> new Exception());
           List<Post> postList = postRepository.findByTopic(foundTopic);
           postList = postService.anonymizeName(postList);
           postService.sortByDateTimeDesc(postList);
           postService.removeDup(postList);
           for(Post post: postList) {
               post.setTopicName(foundTopic.getTopicName());
               post.setUserName(post.getUser().getUsername());
           }
           return postList.subList(0, min(postList.size(), (count+1)*10));
        }
        catch(Exception e) {
            System.out.println("Weird");
            return new ArrayList<Post>();
        }
    }

    @GetMapping(path = "/timeline")
    public List<Post> genUserTimeline(@AuthenticationPrincipal User user, @RequestParam int count){
      List<User> followedUsers = followService.retrieveFollowedUsers(user.getUserID());
        List<Topic> followedTopics = followService.retrieveFollowedTopics(user.getUserID());
        List<Post> allPosts = new ArrayList<Post>();
        for(int i = 0; i < followedUsers.size(); i++){
            allPosts.addAll(postRepository.findByUserAndIsAnonFalse(followedUsers.get(i)));
        }
        for(int i = 0; i < followedTopics.size(); i++){
            allPosts.addAll(postService.anonymizeName(postRepository.findByTopic(followedTopics.get(i))));
        }
        allPosts.addAll(postService.anonymizeName(postRepository.findByUser(user)));
        postService.sortByDateTimeDesc(allPosts);
        List<Post> noDup = postService.removeDup(allPosts);

        List<Post> toReturn = new ArrayList<Post>();
        int i = count;
        System.out.println(i);
        System.out.println(noDup);
        while(i < count+10 && i < noDup.size()){
            toReturn.add(noDup.get(i));
            i++;
        }
        for(Post post: toReturn) {
            post.setUserName(post.getUser().getUsername());
            if(post.getIsAnon()) post.setUserName("Anonymous");
            post.setTopicName(post.getTopic().getTopicName());
        }
        System.out.println(toReturn);
        return toReturn;
    }

    @PostMapping(path="/{postId}")
    public ResponseEntity<?> replyPost(@AuthenticationPrincipal User user, @PathVariable Integer postId, @RequestBody newPostRequest request) {
        System.out.println("Reply called");
        System.out.println(postId);
        try {
            Post parentPost = postRepository.findById(postId).orElseThrow(() -> new Exception());
            Topic topic = parentPost.getTopic();
            Post newPost = new Post(user, parentPost, topic, request.getPostTitle(), request.getPostText(), request.getIsAnon());
            newPost.setPostTime(LocalDateTime.now());
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
            parentPost.setTopicName(parentPost.getTopic().getTopicName());
            parentPost.setUserName(parentPost.getUser().getUsername());
            if(parentPost.getIsAnon()) parentPost.setUserName("Anonymous");
            return parentPost;
        }
        catch (Exception e){
            return null;
        }
    }
}


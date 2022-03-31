package com.javainuse.controllers;

import com.javainuse.classes.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path="/topics")
public class TopicController {
    @Autowired
    private TopicRepository topicRepository;
    private PostRepository postRepository;
    private PostService postService;

    @GetMapping
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @GetMapping(path="/{topicID}")
    public List<Post> getAllPostInTopic(@PathVariable Integer topicID) {
        return null;
    }

    @GetMapping(path="/getTopic/{topicID}")
    public Topic getCurrentTopic(@PathVariable Integer topicID) {
        return topicRepository.findByTopicID(topicID).orElse(null);
    }
}

package com.javainuse;

import com.javainuse.classes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PostTests {

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TopicRepository topicRepository;
    @BeforeEach
    void setup() {
        postService = new PostService();

    }

    @Test
    void testPostSorting(){
        List<Post> sorted = new ArrayList<>();
        List<Post> toSort = sorted;
        for(int i = 0; i < 6; i++){
            Post post = new Post();
            post.setIsAnon(false);
            post.setPostID(i);
            LocalDateTime time = LocalDateTime.now();
            post.setPostTime(time);
            post.setTopicName("testing");
            String testText = Integer.toString(i);
            post.setPostText(testText);
            sorted.add(post);
            toSort.add(0,post);
        }
        postService.sortByDateTimeDesc(toSort);

        assert (toSort.equals(sorted));
    }

    @Test
    void testAnonymity(){
        Post post = new Post();
        post.setUserName("tester1");
        post.setIsAnon(true);
        Post post2 = new Post();
        post2.setUserName("tester2");
        post2.setIsAnon(false);
        List<Post> toObscure = new ArrayList<>();
        toObscure.add(post);
        toObscure.add(post2);
        toObscure = postService.anonymizeName(toObscure);
        assert(toObscure.get(0).getUserName() == "Anonymous" && toObscure.get(1).getUserName() == "tester2");
    }
    @Test
    void testPost(){
        User user;
        try {
            user = userRepository.findByEmail("tester1@gmail.com").orElseThrow(() -> new Exception());
        }
        catch(Exception e) {
            user = new User("tester1","username1","password1","tester1@gmail.com",null,null,null,null,false,true);
            userRepository.save(user);
        }
        Topic topic;
        try {
            topic = topicRepository.findByTopicName("test").orElseThrow(() -> new Exception());
        }
        catch(Exception e) {
            topic = new Topic("test");
            topicRepository.save(topic);
        }
        Post post = new Post(user,null,topic,"title","test post",false);
        post.setPostTime(LocalDateTime.now());
        postRepository.save(post);
        Post doesExist = postRepository.findByPostID(post.getPostID()).orElseThrow();
        assert(doesExist.getPostID() == post.getPostID());
    }



}

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
import java.util.Random;

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

    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setup() {
        postService = new PostService();
        try {
            user1 = userRepository.findByEmail("tester1@gmail.com").orElseThrow(() -> new Exception());
        }
        catch(Exception e) {
            user1 = new User("tester1","username1","password1","tester1@gmail.com",null,null,null,null,false,true);
            userRepository.save(user1);
        }
        try {
            user2 = userRepository.findByEmail("tester2@gmail.com").orElseThrow(() -> new Exception());
        }
        catch(Exception e) {
            user2 = new User("tester2","username2","password2","tester2@gmail.com",null,null,null,null,false,true);
            userRepository.save(user2);
        }
        try {
            user3 = userRepository.findByEmail("tester3@gmail.com").orElseThrow(() -> new Exception());
        }
        catch(Exception e) {
            user3 = new User("tester3","username3","password3","tester3@gmail.com",null,null,null,null,false,true);
            userRepository.save(user3);
        }
    }

    @Test
    void testPostSorting(){ //tests User story 3 and User story 14 Backend sort function(Core to timeline generation)
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
    void testAnonymity(){ //Tests User story 4 acceptance criteria 2 and 3
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
    void testPost(){ //Tests

        Topic topic;
        try {
            topic = topicRepository.findByTopicName("test").orElseThrow(() -> new Exception());
        }
        catch(Exception e) {
            topic = new Topic("test");
            topicRepository.save(topic);
        }
        Post post = new Post(user1,null,topic,"title","test post",false);
        post.setPostTime(LocalDateTime.now());
        postRepository.save(post);
        Post doesExist = postRepository.findByPostID(post.getPostID()).orElseThrow();
        assert(doesExist.getPostID() == post.getPostID());
    }
    @Test //Tests
    void getPostsByTopic(){

    }


    List<Post> genRandomPosts(Topic topic, User user, int count){
        Random random = new Random();
        List<Post> posts = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            String randomBody = random.ints(97, 123).limit(20).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            Post post = new Post(user,null,topic,"title",randomBody,false);
            post.setPostTime(LocalDateTime.now());
            posts.add(post);
        }
        return posts;
    }


}

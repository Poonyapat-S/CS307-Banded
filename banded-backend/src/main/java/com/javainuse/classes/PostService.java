package com.javainuse.classes;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Post> loadByUsername(String username) {
        List<Post> posts = new ArrayList<>();
        try {
            posts = postRepository.findByUser(userRepository.findByUserName(username).orElseThrow(() -> (new UsernameNotFoundException(String.format("", "")))));
        }
        catch(Exception e) {
            posts = new ArrayList<>();
        }
        return posts;
    }

    public List<Post> loadByTopicName(String topicName) {
        List<Post> posts;
        try {
            posts = postRepository.findByTopic(topicRepository.findByTopicName(topicName).orElseThrow(Exception::new));
        }
        catch (Exception e) {
            posts = new ArrayList<>();
        }
        return posts;
    }

    public List<Post> anonymizeName(List<Post> posts){
        List<Post> toReturn = posts;
        for(int i = 0; i < toReturn.size(); i++){
            if (toReturn.get(i).getIsAnon()){
                //iterates through a list of fetched posts and changes all anonymous tagged names to anonymous
                toReturn.get(i).setUserName("Anonymous");
            }
        }
        return toReturn;
    }
    public List<Post> anonymizeForUsers(String username) {
        List<Post> posts = new ArrayList<>();
        try {
            posts = postRepository.findByUserAndIsAnonFalse(userRepository.findByUserName(username).orElseThrow(()
                    -> (new UsernameNotFoundException(String.format("", "")))));
        } catch (Exception e) {
            posts = new ArrayList<>();
        }
        return posts;
    }

    public void sortByDateTimeDesc(List<Post> allPosts) {
        Comparator<Post> dateComparator = Comparator.comparing(Post::getPostTime);
        Collections.sort(allPosts,dateComparator);
        Collections.reverse(allPosts);
    }

    public List<Post> removeDup(List<Post> allPosts) {
        return allPosts.stream().distinct().collect(Collectors.toList());
    }
}

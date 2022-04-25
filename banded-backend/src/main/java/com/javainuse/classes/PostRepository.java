package com.javainuse.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByUserAndIsAnonFalse(User user);
    List<Post> findByTopic(Topic topic);
    List<Post> findByUserAndParentPostIDIsNotNull(User user);
    Optional<Post> findByPostID(Integer postID);
    List<Post> findAll();
}

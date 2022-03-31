package com.javainuse.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    Optional<Topic> findByTopicName(String topicName);
    Optional<Topic> findByTopicID(Integer topicID);
    List<Topic> findAll();

    Boolean existsByTopicName(String topicName);
    Boolean existsByTopicID(Integer topicID);
}

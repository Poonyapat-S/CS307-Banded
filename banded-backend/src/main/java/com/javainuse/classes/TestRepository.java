package com.javainuse.classes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepository extends JpaRepository<User, Integer> {
    //A method that should find a test user object in the database by username
    Optional<TestUser> findByUserName(String username);


}

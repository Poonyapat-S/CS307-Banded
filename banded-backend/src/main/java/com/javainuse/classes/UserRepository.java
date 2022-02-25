package com.javainuse.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User,Integer> {
    //Optional<User> findByUsername(String username);
    //Returns a user based on the given email string
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String userName);
}

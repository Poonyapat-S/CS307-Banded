package com.javainuse.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    TestRepository testRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<TestUser> testUser = testRepository.findByUserName(username);

        testUser.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + username));
        return testUser.map(MyUserDetails::new).get();

    }
}

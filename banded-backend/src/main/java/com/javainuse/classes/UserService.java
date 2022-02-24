package com.javainuse.classes;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "User with email %s not found";
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
    }
    public String deleteByEmail(String email){
        User toDelete=userRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
        userRepository.delete(toDelete);
        return "Deleted";
    }

    //checks databased for a user's email to see if it already exists. Returns true if user exists
    public Boolean duplicateEmail(User user){
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        return userExists;
    }

}

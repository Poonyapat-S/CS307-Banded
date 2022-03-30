package com.javainuse.controllers;

import com.javainuse.classes.DummyUser;
import com.javainuse.classes.User;
import com.javainuse.classes.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Objects;

@RestController
@RequestMapping(path = "/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public DummyUser getCurrentProfile(@AuthenticationPrincipal User user) {
        System.out.println("Got called");
        DummyUser currUser = new DummyUser(user);
        System.out.println(currUser.getName());
        return currUser;
    }

    @GetMapping(path="/{username}")
    public DummyUser getProfile(@AuthenticationPrincipal User user, @PathVariable String username) {
        return new DummyUser(userRepository.findByUserName(username).orElse(null));
    }

    @PutMapping(path="/editbio/{username}")
    public ResponseEntity<?> editProfileBio(@AuthenticationPrincipal User user, @PathVariable String username, @RequestBody String bioText) {
        System.out.println("Called");
        System.out.println(user.getUsername());
        System.out.println(username);
        System.out.println(bioText);
        if(!Objects.equals(user.getUsername(), username)) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }
        user.setBio(bioText);
        userRepository.save(user);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping(path="/delete/{userName}")
    public ResponseEntity<?> deleteProfile(@AuthenticationPrincipal User user, @PathVariable String userName) {
        System.out.println("Delete Called");
        System.out.println(user.getUsername());
        System.out.println(userName);
        if(!Objects.equals(user.getUsername(), userName)) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }
        userRepository.delete(user);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}

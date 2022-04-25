package com.javainuse.controllers;

import com.javainuse.classes.DummyUser;
import com.javainuse.classes.User;
import com.javainuse.classes.UserRepository;
import com.javainuse.registration.StrengthCheck;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Objects;

@RestController
@RequestMapping(path = "/profile")
@AllArgsConstructor
public class ProfileController {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StrengthCheck strengthCheck;


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

    @PostMapping(path="/changeUsername")
    public ResponseEntity<String> changeUsername(@AuthenticationPrincipal User user,@PathVariable String newUsername){
        try {
            userRepository.findByUserName(newUsername).orElseThrow(() -> new UsernameNotFoundException(String.format("", "")));
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Username");
        } catch(UsernameNotFoundException e){
            user.setUserName(newUsername);
            userRepository.save(user);
            return new ResponseEntity("Username successfully changed",HttpStatus.OK);
        }
    }
    @PostMapping(path="/changePassword")
    public ResponseEntity<String> changePassword(@AuthenticationPrincipal User user,@RequestParam String newPassword){
        try{
            String flags = strengthCheck.checkPassword(newPassword);
            if (flags.length() > 0) return ResponseEntity.status(HttpStatus.CONFLICT).body(flags);
            String encoded = passwordEncoder.encode(newPassword);
            User toComp = userRepository.findByUserName(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException(String.format("", "")));
            if(passwordEncoder.matches(newPassword,toComp.getPassword())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Password");
            }
            user.setPassword(encoded);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("Password Successfully Changed!");
        } catch(UsernameNotFoundException e){
            return new ResponseEntity("User not found",HttpStatus.CONFLICT);
        }

    }
}

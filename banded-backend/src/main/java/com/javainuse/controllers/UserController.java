package com.javainuse.controllers;

import com.javainuse.classes.DummyUser;
import com.javainuse.classes.User;
import com.javainuse.classes.UserRepository;
import com.javainuse.classes.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private final UserRepository userRepository;

    @GetMapping
    public String home(@AuthenticationPrincipal User user) {
        String str = "Name: " + user.getName() + '\n';
        str += "Email: " + user.getEmail() + '\n';
        str += "Bio: " + user.getBio() + '\n';
        str += "Favorite band" + user.getFavBand() + '\n';
        str += "Favorite song" + user.getFavSong() + '\n';
        return str;
    }

    @GetMapping(path="/allusers")
    public List<DummyUser> getAllUser() {
        return DummyUser.parseUserList(userRepository.findAll());
    }

    @PostMapping(path = "/bio")
    public String updateBio(@RequestParam String email, @RequestParam String newBio){
        return userService.alterBio(email,newBio);
    }
    @PostMapping(path = "/follow")
    public String followBand(@RequestParam String email, @RequestParam String band){
        return userService.followTopic(email,band);
    }
    @PostMapping(path = "/unfollow")
    public String unfollowBand(@RequestParam String email){
        return userService.unfollowTopic(email);
    }
    @PostMapping(path = "/view")
    public String viewProfile(@RequestParam String username){
        return userService.viewOther(username);
    }


}
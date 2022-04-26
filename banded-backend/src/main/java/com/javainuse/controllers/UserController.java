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
    public String updateBio(@AuthenticationPrincipal User user, @RequestParam String newBio){
        return userService.alterBio(user.getEmail(),newBio);
    }
    /* commenting out below bc it will likely not be used, but will be preserved just in case
    @PostMapping(path = "/follow")
    public String followBand(@AuthenticationPrincipal User user, @RequestParam String band){
        return userService.followTopic(user.getEmail(),band);
    }
    @PostMapping(path = "/unfollow")
    public String unfollowBand(@AuthenticationPrincipal User user){
        return userService.unfollowTopic(user.getEmail());
    }*/
    @PostMapping(path = "/view")
    public String viewProfile(@RequestParam String username){
        return userService.viewOther(username);
    }


}

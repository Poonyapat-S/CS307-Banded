package com.javainuse.controllers;

import com.javainuse.classes.User;
import com.javainuse.classes.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String home(@AuthenticationPrincipal User user) {
        return user.toString();
    }
    @PostMapping(path = "/bio")
    public String updateBio(@RequestParam String email, @RequestParam String newBio){
        return userService.alterBio(email,newBio);
    }
    @PostMapping(path = "/follow")
    public String followBand(@AuthenticationPrincipal User user, @RequestParam String band){
        return userService.followTopic(user.getEmail(),band);
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

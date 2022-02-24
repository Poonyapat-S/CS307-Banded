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
        String str = "Name: " + user.getName() + '\n';
        str += "Email: " + user.getEmail() + '\n';
        str += "Bio: " + user.getBio() + '\n';
        str += "Favorite band" + user.getFavBand() + '\n';
        str += "Favorite song" + user.getFavSong() + '\n';
        return str;
    }
    @PostMapping(path = "/bio")
    public String updateBio(@RequestParam String email, @RequestParam String newBio){
        return userService.alterBio(email,newBio);
    }


}

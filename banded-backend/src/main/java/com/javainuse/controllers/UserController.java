package com.javainuse.controllers;

import com.javainuse.classes.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    @GetMapping
    public String home(@AuthenticationPrincipal User user) {
        String str = "Name: " + user.getName() + '\n';
        str += "Email: " + user.getEmail() + '\n';
        str += "Bio: " + user.getBio() + '\n';
        str += "Favorite band" + user.getFavBand() + '\n';
        str += "Favorite song" + user.getFavSong() + '\n';
        return str;
    }

}

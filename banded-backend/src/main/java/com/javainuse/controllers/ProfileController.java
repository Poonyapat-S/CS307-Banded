package com.javainuse.controllers;

import com.javainuse.classes.DummyUser;
import com.javainuse.classes.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/profile")
public class ProfileController {

    @GetMapping
    public DummyUser getCurrentProfile(Authentication authentication) {
        System.out.println("Got called");
        DummyUser currUser = new DummyUser((User) authentication.getPrincipal());
        System.out.println(currUser.getName());
        return currUser;
    }

}

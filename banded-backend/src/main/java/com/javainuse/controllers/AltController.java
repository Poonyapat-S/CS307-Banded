package com.javainuse.controllers;

import com.javainuse.classes.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class AltController {
    User user = new User("Hello","abc123","poke");
    @GetMapping
    public String test(){
        return user.toString();
    }
    @GetMapping("/")
    public String homePage(){
        return "Homepage";
    }
    @GetMapping("/user")
    public String isUser(){
        return ("Welcome user!");
    }
}

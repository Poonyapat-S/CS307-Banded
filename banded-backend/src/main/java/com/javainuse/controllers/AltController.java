
package com.javainuse.controllers;

import com.javainuse.classes.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class AltController {
    @GetMapping("/login")
    public String homePage(){
        return "This is the temp homepage";
    }
    @GetMapping("/testSession")
    public String testSession(@AuthenticationPrincipal User user){
        return user.toString();
    }
}


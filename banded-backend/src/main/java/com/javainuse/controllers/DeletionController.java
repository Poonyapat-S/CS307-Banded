package com.javainuse.controllers;

import com.javainuse.classes.User;
import com.javainuse.classes.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;

@RestController
@RequestMapping(path = "api/service/delete")
@AllArgsConstructor
public class DeletionController 
{
    @Autowired
    private UserService userService;

    @PostMapping
    public String deleteUser(@AuthenticationPrincipal User user) {
        return userService.deleteByEmail(user.getEmail());
    }
}

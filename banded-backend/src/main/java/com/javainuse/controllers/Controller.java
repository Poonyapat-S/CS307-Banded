package com.javainuse.controllers;

import java.util.ArrayList;
import java.util.List;

import com.javainuse.classes.NewUser;
import com.javainuse.exception.ResourceNotFoundException;
import com.javainuse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javainuse.classes.User;

@RequestMapping(path = "/users")
@RestController
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    UserRepository userRepository;

    private List<User> UserList = new ArrayList<User>();

    @GetMapping(produces = "application/json")
    public List<User> firstPage() {
        UserList = userRepository.findAll();
        return UserList;
    }

    @DeleteMapping(path = { "/{id}" })
    public User delete(@PathVariable("id") Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        for(User u: UserList) {
            if(u.getuserID() == id) {
                UserList.remove(u);
                break;
            }
        }
        userRepository.delete(user);
        return user;
    }

    @PostMapping
    public User create(@RequestBody NewUser u) {
        User user = new User();
        user.setUserName(u.userName);
        user.setPassword(u.password);
        user.setName(u.name);
        UserList.add(user);
        userRepository.save(user);
        return user;
    }


}

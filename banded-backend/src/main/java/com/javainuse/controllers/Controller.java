package com.javainuse.controllers;

import java.util.ArrayList;
import java.util.List;

import com.javainuse.classes.NewUser;
import org.springframework.web.bind.annotation.*;

import com.javainuse.classes.User;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/users")
@RestController
public class Controller {
    private List<User> UserList = createList();

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = "application/json")
    public List<User> firstPage() {
        return UserList;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(path = { "/{id}" })
    public User delete(@PathVariable("id") String id) {
        User deletedUser = null;
        for(User user: UserList) {
            if(user.getuserID().equals(id)) {
                deletedUser = user;
                UserList.remove(user);
                break;
            }
        }
        return deletedUser;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public User create(@RequestBody NewUser user) {
        User u = new User(user);
        UserList.add(u);
        System.out.println(UserList);
        return u;
    }

    private static List<User> createList() {
        List<User> UserList = new ArrayList<>();
        User usr1 = new User("1234", "ABCD", "ABCD");
        User usr2 = new User("5678", "EFGH", "IJKL");
        UserList.add(usr1);
        UserList.add(usr2);
        return UserList;
    }

}

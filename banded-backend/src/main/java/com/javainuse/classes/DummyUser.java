package com.javainuse.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DummyUser {
    private Integer userID;
    private String name;
    private String userName;
    @JsonIgnore
    private String password;
    private String email = "user@example.com";
    private String bio;
    private String favBand;
    private String favSong;

    public DummyUser(User user) {
        this.userID = user.getUserID();
        this.name = user.getName();
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.bio = user.getBio();
        this.favBand = user.getFavBand();
        this.favSong = user.getFavSong();
    }

    @JsonIgnore
    public static DummyUser parseUser(User user) {
        return new DummyUser(user);
    }

    @JsonIgnore
    public static List<DummyUser> parseUserList(List<User> userList) {
        List<DummyUser> DUList = new ArrayList<DummyUser>();
        for(User user:userList) {
            DUList.add(new DummyUser(user));
        }
        return DUList;
    }
}

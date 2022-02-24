package com.javainuse.classes;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "user")

public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userID")
    private Integer userID;
    private String name;
    @Column(name="username")
    private String userName;
    private String password;
    private String email = "user@example.com";
    private String bio;
    private String favBand;
    private String favSong;

    public User() {}

    public User(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public User(NewUser user) {
        this(user.name, user.userName, user.password);
    }

    public Integer getuserID() {
        return userID;
    }

    @Override
    public int hashCode() {
        return userID.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        User usr = (User) obj;
        return this.userID.equals(usr.getuserID());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserID(String userID) {
        this.userID = Integer.valueOf(userID);
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFavBand() {
        return favBand;
    }

    public void setFavBand(String favBand) {
        this.favBand = favBand;
    }

    public String getFavSong() {
        return favSong;
    }

    public void setFavSong(String favSong) {
        this.favSong = favSong;
    }
}

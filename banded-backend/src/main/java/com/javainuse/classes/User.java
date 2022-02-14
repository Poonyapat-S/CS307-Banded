package com.javainuse.classes;

import java.util.Random;

public class User extends NewUser{

    private String userID;
    private String name;
    private String userName;
    private String password;

    public User(String name, String userName, String password) {
        super(name, userName, password);
        Random rand = new Random();
        Integer int_random = (Integer) rand.nextInt((int) 1e9+1);
        userID = int_random.toString();
    }

    public User(NewUser user) {
        this(user.name, user.userName, user.password);
    }

    public String getuserID() {
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

}

package com.javainuse.classes;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "User with email %s not found";
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND,username)));
        System.out.println(user.getPassword());
        return user;
    }
    public String deleteByEmail(String email){
        User toDelete=userRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
        userRepository.delete(toDelete);
        return "Deleted";
    }

    //checks databased for a user's email to see if it already exists. Returns true if user exists
    public Boolean duplicateEmail(User user){
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        return userExists;
    }
    public String alterBio(String email, String bio){
        if(bio.length() > 280){
            return "bio is too long";
        }
        else{
            User toUpdate = userRepository.findByEmail(email).orElseThrow(()
                    -> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
            toUpdate.setBio(bio);
            userRepository.save(toUpdate);
            return toUpdate.toString();
        }
    }
    public String followTopic(String email,String band){
        User toUpdate = userRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
        if(toUpdate.getFavBand() != band){
            toUpdate.setFavBand(band);
            userRepository.save(toUpdate);
            return String.format("Followed %s",band);
        }
        else{
            return "This band is already your favorite!";
        }
    }
    public String unfollowTopic(String email){
        User toUpdate = userRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
        if(toUpdate.getFavBand() != null){
            toUpdate.setFavBand(null);
            userRepository.save(toUpdate);
            return "Unfollowed your favorite band";
        }
        else{
            return "You don't have a favorite band!";
        }
    }
    //allows us to view other users
    public String viewOther(String username){
        User toView = userRepository.findByUserName(username).orElseThrow(()
                -> new UsernameNotFoundException(String.format(USER_NOT_FOUND,username)));
        return toView.toString();
    }


}

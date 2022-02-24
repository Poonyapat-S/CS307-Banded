package com.javainuse.registration;


import com.javainuse.classes.User;
import com.javainuse.classes.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailValidator emailValidator;

    //The register method. This will place a new user in the database when called.
    public String register(RegistrationRequest userReg) {
        try {
            Optional<User> tempList;
            tempList = userRepository.findByEmail(userReg.getEmail());
            if(tempList.isPresent()) return "duplicate email";
            userRepository.findByUserName(userReg.getUsername()).orElseThrow(() -> new UsernameNotFoundException(String.format("", "")));
            return "duplicate username";
        } catch(UsernameNotFoundException e) {
            System.out.println("Unique email");
            if (emailValidator.validateEmail(userReg.getEmail()) == false) {
                return "didnt work";
            }
            User user = new User();
            user.setName(userReg.getName());
            user.setUserName(userReg.getUsername());
            user.setPassword(passwordEncoder.encode(userReg.getPassword()));
            user.setEmail(userReg.getEmail());
            userRepository.save(user);
            return "works";
        }
    }
}

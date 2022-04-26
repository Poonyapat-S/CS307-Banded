package com.javainuse.registration;


import com.javainuse.classes.User;
import com.javainuse.classes.UserRepository;
import com.javainuse.sec.PasswordEncoderProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailValidator emailValidator;
    private final StrengthCheck strengthCheck;
	
	//The register method. This will place a new user in the database when called.
    public ResponseEntity<String> register(RegistrationRequest userReg) {
        try {
            Optional<User> tempList;
            tempList = userRepository.findByEmail(userReg.getEmail());
            if(tempList.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Email");
            String flags = strengthCheck.checkPassword(userReg.getPassword());
            if (flags.length() > 0) return ResponseEntity.status(HttpStatus.CONFLICT).body(flags);
            userRepository.findByUserName(userReg.getUserName()).orElseThrow(() -> new UsernameNotFoundException(String.format("", "")));
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Username");
        } catch(UsernameNotFoundException e) {
            if (emailValidator.validateEmail(userReg.getEmail()) == false) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid Email");
            }
            User user = new User();
            user.setName(userReg.getName());
            user.setUserName(userReg.getUserName());
            user.setPassword(passwordEncoder.encode(userReg.getPassword()));
            user.setEmail(userReg.getEmail());
            user.setInvalidTries(0);
            user.setLocked(false);
            userRepository.save(user);
            return new ResponseEntity("User registered successfully", HttpStatus.OK);
        }
    }
}

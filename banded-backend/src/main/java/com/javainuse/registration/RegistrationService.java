package com.javainuse.registration;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidator emailValidator;
    //The register method. This will place a new user in the database when called.
    public String register(String email) {
        if(emailValidator.validateEmail(email) == false){
            return "didnt work";
        }
        return "works";
    }
}

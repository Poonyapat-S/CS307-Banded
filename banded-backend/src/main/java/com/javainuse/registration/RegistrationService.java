package com.javainuse.registration;


import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    //The register method. This will place a new user in the database when called.
    public String register(RegistrationRequest request) {
        return "works";
    }
}

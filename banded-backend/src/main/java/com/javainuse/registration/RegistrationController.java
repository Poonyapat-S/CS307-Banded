package com.javainuse.registration;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    //Takes in the object sent over from localhost/api/v1/registration and verifies passes it as a registeration request
    //object to the register method in registration service. If the input is not formatted as a regRequest it will error
    // TODO find a way to actually send over a registration object, idk how
    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequest RR){
        System.out.println("Received Request");
        return registrationService.register(RR);
    }
}

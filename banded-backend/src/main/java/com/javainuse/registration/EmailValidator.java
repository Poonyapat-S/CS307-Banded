package com.javainuse.registration;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator {
    public Boolean validateEmail(String email){
        //Using the OWASP email validation regex(a standard for industry)
        return Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
                .matcher(email).matches();
    }
}

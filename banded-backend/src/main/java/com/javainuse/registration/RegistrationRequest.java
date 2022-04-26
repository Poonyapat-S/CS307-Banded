package com.javainuse.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RegistrationRequest {
    //This request serves as the body of what needs to be sent over for when the user registers.
    // TODO add the parameters we want a user object to start with when they register
    private String name;
    private String userName;
    private String email;
    private String password;
}

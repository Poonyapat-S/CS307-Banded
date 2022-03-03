package com.javainuse.sec;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Credentials {
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("password")
    private String password;
    public Credentials() {}
}

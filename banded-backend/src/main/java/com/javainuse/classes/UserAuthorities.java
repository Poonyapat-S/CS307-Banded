package com.javainuse.classes;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthorities implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}

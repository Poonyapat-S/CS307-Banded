package com.javainuse.sec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javainuse.classes.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserRepository userRepo;

    public CustomAuthenticationFilter(AuthenticationManager authManager, UserRepository userRepo) {
        super();
        this.authManager = authManager;
        this.userRepo = userRepo;
    }

    public CustomAuthenticationFilter() {
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            // Get username & password from request (JSON) any way you like
            ObjectMapper mapper = new ObjectMapper();
            Credentials creds = mapper.readValue(request.getInputStream(),Credentials.class);
            Authentication auth = new UsernamePasswordAuthenticationToken(creds.getUserName(),
                   creds.getPassword());
            System.out.println("USNAME: " + request.getParameter("username"));
            return authManager.authenticate(auth);
        } catch (Exception exp) {
            throw new RuntimeException(exp);
        }
    }
}

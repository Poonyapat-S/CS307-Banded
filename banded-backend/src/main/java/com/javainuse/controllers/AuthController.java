package com.javainuse.controllers;

import com.javainuse.classes.JwtResponse;
import com.javainuse.classes.User;
import com.javainuse.classes.UserRepository;
import com.javainuse.sec.Credentials;
import com.javainuse.util.JwtUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.pow;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Credentials loginRequest) {

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
            User foundUser = userRepository.findByUserName(loginRequest.getUserName()).orElse(null);
            foundUser.setInvalidTries(0);
            foundUser.setLockedUntil(null);
            userRepository.save(foundUser);
            System.out.println("username: " + loginRequest.getUserName());
            System.out.println("password: " + loginRequest.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtil.generateJwtToken(authentication);

            User userDetails = (User) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getUserID(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        } catch (BadCredentialsException e) {
            System.out.println("Get Fucked");
            User foundUser = userRepository.findByUserName(loginRequest.getUserName()).orElse(null);
            if (foundUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username/Password");
            }
            LocalDateTime now = LocalDateTime.now();
            if(foundUser.getLockedUntil() != null) {
                System.out.println("Here");
                System.out.println(now);
                System.out.println(foundUser.getLockedUntil().toLocalDateTime());
                if(now.isBefore(foundUser.getLockedUntil().toLocalDateTime())) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    String formatDateTime = foundUser.getLockedUntil().toLocalDateTime().format(formatter);
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Your account is locked until " + formatDateTime);
                }
            }

            Integer invalidTries = foundUser.getInvalidTries();
            invalidTries++;
            foundUser.setInvalidTries(invalidTries);
            if(invalidTries >= 10) {
                foundUser.setLocked(true);
                userRepository.save(foundUser);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account Locked. Please contact admin.");
            }
            else if(invalidTries >= 6) {
                LocalDateTime newTime = now.plusMinutes((long) pow(2, invalidTries-6));
                foundUser.setLockedUntil(Timestamp.valueOf(newTime));
                userRepository.save(foundUser);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                String formatDateTime = newTime.format(formatter);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Your account is locked until " + formatDateTime);
            }
            else {
                userRepository.save(foundUser);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Your account will be locked after " + (6-invalidTries) + " more invalid attempts");
            }

        } catch (LockedException e) {
            System.out.println("Fuck you");
            User foundUser = userRepository.findByUserName(loginRequest.getUserName()).orElse(null);
            if (foundUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username/Password");
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account Locked. Please contact admin.");
        } catch(Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username/Password");
        }
    }
}

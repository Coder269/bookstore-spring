package com.abbas.bookstore.controller;


import com.abbas.bookstore.model.Role;
import com.abbas.bookstore.model.User;
import com.abbas.bookstore.model.auth.AuthenticationResponse;
import com.abbas.bookstore.model.auth.SigninRequest;
import com.abbas.bookstore.model.auth.SignupRequest;
import com.abbas.bookstore.security.JwtUtils;
import com.abbas.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/authentication")
public class AuthenticationController {


    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {
        if (userService.findUserByEmail(signupRequest.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        User newUser = new User();
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(signupRequest.getPassword());
        newUser.setFirstname(signupRequest.getFirstname());
        newUser.setLastname(signupRequest.getLastname());

        userService.createUser(newUser);

      return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> signIn(@RequestBody SigninRequest signinRequest) {

        User user = userService.findUserByEmail(signinRequest.getEmail());

        if (user == null )
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email not found!");
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>()
        );

        if (!passwordEncoder.matches(signinRequest.getPassword(), userDetails.getPassword()))
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password in not correct!");
        }

        String jwt = jwtUtil.generateToken(userDetails);

        // Return the JWT and the role in the response
        AuthenticationResponse response = new AuthenticationResponse(jwt);
        response.setId(user.getId());

        return ResponseEntity.ok(response);
    }
}





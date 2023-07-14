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
    private AuthenticationManager authenticationManager;


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
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signinRequest.getEmail(),
                            signinRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        User user = userService.findUserByEmail(signinRequest.getEmail());

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>()
        );

        String jwt = jwtUtil.generateToken(userDetails);

        // Extract the role from the User object
        Role role = user.getRole();

        // Return the JWT and the role in the response
        AuthenticationResponse response = new AuthenticationResponse(jwt);
        response.setRole(role);
        response.setFirstname(user.getFirstname());
        response.setLastname(user.getLastname());
        response.setId(user.getId());
        response.setPassword(user.getPassword());
        response.setEmail(user.getEmail());

        return ResponseEntity.ok(response);
    }
}





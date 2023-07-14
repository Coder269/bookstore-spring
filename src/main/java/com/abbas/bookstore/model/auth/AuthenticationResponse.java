package com.abbas.bookstore.model.auth;

import com.abbas.bookstore.model.Role;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private final String jwt;
    private String firstname;
    private String lastname;
    private Role role;
    private Long id;
    private String email;
    private String password;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

}

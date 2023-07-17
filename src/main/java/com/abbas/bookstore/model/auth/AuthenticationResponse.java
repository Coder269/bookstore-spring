package com.abbas.bookstore.model.auth;

import com.abbas.bookstore.model.Role;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private final String jwt;
    private Long id;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

}

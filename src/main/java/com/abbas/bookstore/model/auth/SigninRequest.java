package com.abbas.bookstore.model.auth;

import com.abbas.bookstore.model.Role;
import lombok.Data;

@Data
public class SigninRequest {

    private String email;
    private String password;

}

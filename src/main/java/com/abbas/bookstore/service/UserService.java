package com.abbas.bookstore.service;

import com.abbas.bookstore.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    User createUser(User user);

    User updateUser(User user);

    void deleteUserById(Long id);

    User findUserByEmail(String email);

    List<User> findAllUsers();

    @Transactional
    void makeAdmin(String email);

}

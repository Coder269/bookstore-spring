package com.abbas.bookstore.service;

import com.abbas.bookstore.model.Role;
import com.abbas.bookstore.model.User;
import com.abbas.bookstore.repository.UserRepository;
import com.abbas.bookstore.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private final JwtUtils jwtUtils;

    @Autowired
    private final AuthenticationManager authManager;


    @Override
    public User createUser(User user){
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user){
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void makeAdmin(String email){
        userRepository.updateUserRole(email, Role.ADMIN);
    }

    @Override
    @Transactional
    public void updateUserFirstname(Long id, String firstname){
        userRepository.updateUserFirstname(id, firstname);
    }

    @Override
    @Transactional
    public void updateUserLastname(Long id, String lastname){
        userRepository.updateUserLastname(id, lastname);
    }

    @Override
    @Transactional
    public void updateUserEmail(Long id, String email){
        userRepository.updateUserEmail(id, email);
    }

    @Override
    public User findUserById(Long id) { return userRepository.findById(id).orElse(null);  }
}
